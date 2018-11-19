package com.igreatstone.partyedu;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.JsonObject;
import com.igreatstone.partyedu.common.ToastUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.igreatstone.partyedu.common.CommonUtil;
import com.igreatstone.partyedu.common.CustomVideoView;
import com.igreatstone.partyedu.model.VideoPlayBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class VideoActivity extends Activity {
    private final static String FAG = "VideoActivity";
    private CustomVideoView vvPlayer;
    private TextView tvSeepSpeak;
    private RelativeLayout rlControler;
    private ProgressBar progress;
    private TextView tvTime;
    private boolean isLongPressKey = false;
    private ImageView controlImage;
    private int saveHitTimes = 0;
    //视频播放相关数据
    private String videoData;
    private VideoPlayBean videoplayBean;
    //是否完成了播放
    private String isComplete = "1";
    //播放日誌ID

    private String logPlayId = "";
    //是否继续播放0代表从头播放，1代表上传观看
    private int ifstart;
    private int total_len;
    //保存退出的dialog
    private Dialog savePlayLog;
    //是否正在保存
    private boolean isSaving = false;
    private int lastPlayTime;
    private int played_len = 0;
    private Timer runTimer = new Timer();//记录播放时长、控制进度条等
    private Timer payerLogTimer = new Timer();//采集上报播放日志
    //是否正在快进
    private Boolean isQuicking = false;
    private int curQuickTimes = 0;
    private int maxQuickTimes = 32;

    private ProgressBar videoProgressBar;

    public static final int HIDE_CONTROL_BAR = 0x02;// 隐藏控制条
    private Handler handlerMsg = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    String playUrl = videoplayBean.getVideoUrl();
                    if (playUrl != null && playUrl.length() > 0) {
                        vvPlayer.setVideoURI(Uri.parse(playUrl));
                        if (ifstart == 0) {
                            vvPlayer.seekTo(0);
                            savePlayTime(videoplayBean, 0);
                        } else if (ifstart == 1 && lastPlayTime > 0) {
                            int i = lastPlayTime * 1000;
                            Log.i("lastPlayTime", i + "");
                            vvPlayer.seekTo(i);
                        }
                    }
                    break;
                case HIDE_CONTROL_BAR:
                    vvPlayer.start();
                    videoProgressBar.setVisibility(View.GONE);
                    break;
            }
        }
    };

    Runnable readyPlay = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = new Message();
            message.what = 100;
            handlerMsg.sendMessage(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initView();

        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Bundle bundle = getIntent().getExtras();
        //视频播放相关数据
        videoData = bundle.getString("videoData");

        Log.i("tv_playLog", "videoData: -------------");
        if (videoData == null) {
            Log.e("tv_playLog", "videoData is null");
            return;
        }

        videoplayBean = new Gson().fromJson(videoData, VideoPlayBean.class);

        ifstart = videoplayBean.getIfStart();

        /*lastPlayTime = Integer.valueOf(StringUtils.isEmpty(videoplayBean.getLastPlayTimes()) ?
                                "0" : videoplayBean.getLastPlayTimes());*/
        lastPlayTime = getLastPlayTime(videoplayBean);//上次播放时间

        runTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        total_len = vvPlayer.getDuration();
                        int played_len = vvPlayer.getCurrentPosition();
                        if (total_len > 0) {
                            progress.setProgress(played_len);
                            progress.setMax(total_len);
                            tvTime.setText(getTime(played_len) + "/" + getTime(total_len));
                        }
                        if (vvPlayer.isPlaying()) {
                            saveHitTimes++;
                            Log.d("played_len", played_len + "total_len: -------------" + total_len);
                            if (total_len > 0 && played_len > total_len - 2000) {
                                runTimer.cancel();
                                payerLogTimer.cancel();
                                isComplete = "0";
                                synPlayLog(saveHitTimes, isComplete);
                                Log.d("play_time", saveHitTimes + "isComplete: -------------" + isComplete);
                            }
                            savePlayTime(videoplayBean, played_len/1000);
                        }

                    }
                });
            }
        }, 0, 1000);


        final int heartTime = TextUtils.isEmpty(videoplayBean.getPlayTime()) ? 30000 : Integer.parseInt(videoplayBean.getPlayTime());
        payerLogTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        synPlayLog(saveHitTimes, isComplete);
                        Log.d("play_time", saveHitTimes + "isComplete: -------------" + isComplete);
                    }
                });
            }
        }, 0, heartTime);

        // 监听播放完成的事件�?
        vvPlayer.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });

        // 监听视频装载完成的事件�?
        vvPlayer.setOnPreparedListener(new OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
/*                if (ifstart == 0) {
                    handlerMsg.removeMessages(HIDE_CONTROL_BAR);
                    handlerMsg.sendEmptyMessage(HIDE_CONTROL_BAR);
                }*/

                handlerMsg.removeMessages(HIDE_CONTROL_BAR);
                handlerMsg.sendEmptyMessage(HIDE_CONTROL_BAR);

               /* mp.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                    @Override
                    public void onSeekComplete(MediaPlayer mp) {
                        handlerMsg.removeMessages(HIDE_CONTROL_BAR);
                        handlerMsg.sendEmptyMessage(HIDE_CONTROL_BAR);
                    }
                });
*/

            }
        });


        // 监听播放发生错误时�?的事件�?
        vvPlayer.setOnErrorListener(new OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                handlerMsg.removeMessages(HIDE_CONTROL_BAR);
                handlerMsg.sendEmptyMessage(HIDE_CONTROL_BAR);
                return false;
            }
        });
        new Thread(readyPlay).start();
    }

    /**
     * 在线log
     * @param playlogtime
     * @param isComplete
     */
    private void synPlayLog(int playlogtime, final String isComplete) {
        String playlogTime = String.valueOf(playlogtime);
        playlogTime = StringUtils.isEmpty(playlogTime)?"0":playlogTime;
        String last = String.valueOf(playlogtime + lastPlayTime);
        String url = videoplayBean.getIpAddress() + "&vId=" + videoplayBean.getVideoId() + "&vType=" + videoplayBean.getVType() + "&course_id=" + videoplayBean.getCourseId() + "&templateId=" + videoplayBean.getMenuId() + "&menuName=" + videoplayBean.getMenuName()
                + "&player_time=" + playlogTime + "&menuId=" + videoplayBean.getMenuId() + "&lastPlayTimes=" + last + "&isComplete=" + isComplete + "&logPlayId=" + logPlayId + "&loginToken=" + videoplayBean.getLoginToken() + "&vName=" + videoplayBean.getvName() + "&vLength=" + total_len;
        Log.i("tv_playLog", "synPlayLog,logPlayId is:" + logPlayId);
        OkGo.<String>get(url).tag("this").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String playlogData = response.body();
                if (!TextUtils.isEmpty(playlogData)) {
                    try {
                        JSONObject js = new JSONObject(playlogData);
                        int status = js.optInt("status");
                        logPlayId = js.optString("logPlayId");
                        String msg = js.optString("msg");
                        if (status == 1) {
                            if (("0").equals(isComplete)) {
                                finish();
                            }
                            Log.i("dj_tv", js.getString("msg"));
                        } else {
                            ToastUtil.showLong(VideoActivity.this, msg);
                            finish();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });

    }

    /**
     * 初始化view
     */
    private void initView() {
        tvSeepSpeak = (TextView) findViewById(R.id.tvSeepSpeak);
        rlControler = (RelativeLayout) findViewById(R.id.rlControler);
        vvPlayer = (CustomVideoView) findViewById(R.id.vvPlayer);
        progress = (ProgressBar) findViewById(R.id.progress);
        tvTime = (TextView) findViewById(R.id.tvTime);
        videoProgressBar = (ProgressBar) this.findViewById(R.id.videoProgressBar);
        controlImage = (ImageView) findViewById(R.id.control);
        rlControler.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStop() {
        payerLogTimer.cancel();
        runTimer.cancel();
        super.onStop();
    }

    public static String getTime(long oSecond) {
        StringBuffer sbTime = new StringBuffer();
        long tmpTime = oSecond / 3600000;
        int hour = (int) tmpTime;

        oSecond -= hour * 3600000;
        tmpTime = oSecond / 60000;
        int minite = (int) tmpTime;

        oSecond -= minite * 60000;
        int second = (int) (oSecond / 1000);
        String hourStr = (hour < 10) ? ("0" + hour) : String.valueOf(hour);
        sbTime.append(hourStr);
        sbTime.append(":");
        String miniteStr = (minite < 10) ? ("0" + minite) : String
                .valueOf(minite);
        sbTime.append(miniteStr);
        sbTime.append(":");
        String secondStr = (second < 10) ? ("0" + second) : String
                .valueOf(second);
        sbTime.append(secondStr);
        return sbTime.toString();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == 23) {// 确认
            if (vvPlayer.isPlaying()) {
                vvPlayer.pause();
                controlImage.setImageResource(R.mipmap.resume);
                rlControler.setVisibility(View.VISIBLE);
            } else {
                controlImage.setImageResource(R.mipmap.pause);
                vvPlayer.start();
                rlControler.setVisibility(View.GONE);
            }
        } else if (keyCode == 126) {// 播放
            if (!vvPlayer.isPlaying()) {
                vvPlayer.start();
                rlControler.setVisibility(View.GONE);
            }
        } else if (keyCode == 121) {
            if (vvPlayer.isPlaying()) {
                vvPlayer.pause();
                rlControler.setVisibility(View.VISIBLE);
            }
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP) {
            finish();
        } else if (keyCode == 22 || keyCode == 125) {

        } else if (keyCode == 21 || keyCode == 89) {

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == 22 || keyCode == 125) {
            if (isLongPressKey) {
                isLongPressKey = false;
            }
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isSaving) {
                //synPlayLog(saveHitTimes, isComplete);
                finish();
            }
            return false;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.d("keyCode", keyCode + "");
        isLongPressKey = true;
        // TODO Auto-generated method stub
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        vvPlayer.stopPlayback();
    }

    public void onPause() {
        super.onPause();
        vvPlayer.stopPlayback();
    }

    private void savePlayTime(VideoPlayBean bean, int playTime){
        if(bean != null && !TextUtils.isEmpty(bean.getVideoId())){
            if(!TextUtils.isEmpty(bean.getVideoId())){
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(bean.getVideoId(), playTime);
                    CacheUtils.getInstance().put(bean.getVideoId(), jsonObject);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    private int getLastPlayTime(VideoPlayBean bean){
        if(bean != null && !TextUtils.isEmpty(bean.getVideoId())){
            try {
                JSONObject jsonObject = CacheUtils.getInstance().getJSONObject(bean.getVideoId());
                String lastPlayTimeStr = jsonObject == null ? "" : jsonObject.getString(bean.getVideoId());
                int lastPayTime = TextUtils.isEmpty(lastPlayTimeStr) ? 0 : Integer.parseInt(lastPlayTimeStr);
                return lastPayTime;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

}
