package com.igreatstone.partyedu;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Transition;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.igreatstone.base.mobile.player.listener.OnTransitionListener;
import com.igreatstone.base.mobile.player.model.SwitchVideoModel;
import com.igreatstone.base.mobile.player.video.SampleVideo;
import com.igreatstone.partyedu.model.VideoPlayBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

/**
 * 单独的视频播放页面
 * Created by shuyu on 2016/11/11.
 */
public class PlayActivity1 extends AppCompatActivity {

    //保存退出的dialog
    private ProgressDialog savePlayLog;
    //播放日誌ID

    private String logPlayId = "";
    //视频总长度
    private long total_len;
    //是否正在保存
    private boolean isSaving = false;
    private String lastPlayTime;
    private int play_time = 0;
    //视频播放相关数据
    private String videoData;
    private VideoPlayBean videoplayBean;

    //是否完成了播放
    private String isComplete = "1";

    private int saveHitTimes = 0;
    private Timer runTimer = new Timer();



    public final static String IMG_TRANSITION = "IMG_TRANSITION";
    public final static String TRANSITION = "TRANSITION";

//    @BindView(R.id.video_player)
    SampleVideo videoPlayer;

    OrientationUtils orientationUtils;

    private boolean isTransition;

    private Transition transition;
    private String url;
    private boolean isLive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        videoPlayer= (SampleVideo) findViewById(R.id.video_player);
        Bundle bundle = getIntent().getExtras();
        videoData = bundle.getString("videoData");
        videoplayBean = new Gson().fromJson(videoData, VideoPlayBean.class);


        ButterKnife.bind(this);
        isTransition = getIntent().getBooleanExtra(TRANSITION, false);
        init();
    }

    private void init() {


        String name = "普通";
        SwitchVideoModel switchVideoModel = new SwitchVideoModel(name, videoplayBean.getVideoUrl());

        String source2 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4";
        String name2 = "清晰";
        SwitchVideoModel switchVideoModel2 = new SwitchVideoModel(name2, source2);

        List<SwitchVideoModel> list = new ArrayList<>();
        list.add(switchVideoModel);
//        list.add(switchVideoModel2);

        videoPlayer.setUp(list, true, "");

//        //增加封面
//        ImageView imageView = new ImageView(this);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(R.mipmap.xxx1);
//        videoPlayer.setThumbImageView(imageView);

        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //videoPlayer.setShowPauseCover(false);

        //videoPlayer.setSpeed(2f);

        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);

        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);

        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });

        //videoPlayer.setBottomProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_progress));
        //videoPlayer.setDialogVolumeProgressBar(getResources().getDrawable(R.drawable.video_new_volume_progress_bg));
        //videoPlayer.setDialogProgressBar(getResources().getDrawable(R.drawable.video_new_progress));
        //videoPlayer.setBottomShowProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_seekbar_progress),
                //getResources().getDrawable(R.drawable.video_new_seekbar_thumb));
        //videoPlayer.setDialogProgressColor(getResources().getColor(R.color.colorAccent), -11);

        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);


        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //过渡动画
        initTransition();
        takeData();

    }


    private void takeData(){

        runTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        total_len = GSYVideoManager.instance().getMediaPlayer().getDuration();
                        long played_len = GSYVideoManager.instance().getMediaPlayer().getCurrentPosition();
                        if (GSYVideoManager.instance().getMediaPlayer().isPlaying()) {
                            saveHitTimes++;
                            String playTime = videoplayBean.getPlayTime();
                            int playtime = Integer.parseInt(playTime) / 1000;
                            if (saveHitTimes % playtime == 0) {
                                if (GSYVideoManager.instance().getMediaPlayer().isPlaying()) {
                                    play_time = saveHitTimes;
//                                if (played_len == saveHitTimes - 2000) {
//                                    isComplete = "0";
//                                }
                                    synPlayLog(play_time, isComplete);
//                                    Log.e("play_time", play_time + "play_time: -------------" + isComplete);
                                }
                            }
                            Log.e("played_len", played_len + "total_len: -------------" + total_len);
                            if (played_len > total_len - 2000 && total_len > 0) {
                                runTimer.cancel();
                                isComplete = "0";
                                closeVideo(saveHitTimes, isComplete);
                                Log.e("saveHitTimes", saveHitTimes + "saveHitTimes: -------------" + isComplete);
                            }
                        }

                    }
                });
            }
        }, 0, 1000);
    }
    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
        takeData();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setStandardVideoAllCallBack(null);
        GSYVideoPlayer.releaseAllVideos();
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onBackPressed();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
            }, 500);
        }
    }


    private void initTransition() {
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            ViewCompat.setTransitionName(videoPlayer, IMG_TRANSITION);
            addTransitionListener();
            startPostponedEnterTransition();
        } else {
            videoPlayer.startPlayLogic();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean addTransitionListener() {
        transition = getWindow().getSharedElementEnterTransition();
        if (transition != null) {
            transition.addListener(new OnTransitionListener(){
                @Override
                public void onTransitionEnd(Transition transition) {
                    super.onTransitionEnd(transition);
                    videoPlayer.startPlayLogic();
                    transition.removeListener(this);
                }
            });
            return true;
        }
        return false;
    }



    /**
     * 在线log
     *
     * @param playlogtime
     * @param isComplete
     */

    private void synPlayLog(int playlogtime, final String isComplete) {
        String playlogTime = String.valueOf(playlogtime);
        String last = String.valueOf(playlogtime);
        String url = videoplayBean.getIpAddress() + "&vId=" + videoplayBean.getVideoId() + "&vType=" + videoplayBean.getVType() + "&course_id=" + videoplayBean.getCourseId() + "&templateId=" + videoplayBean.getMenuId() + "&menuName=" + videoplayBean.getMenuName()
                + "&player_time=" + playlogTime + "&menuId=" + videoplayBean.getMenuId() + "&lastPlayTimes=" + last + "&isComplete=" + isComplete + "&logPlayId=" + logPlayId + "&loginToken=" + videoplayBean.getLoginToken() + "&vName=" + videoplayBean.getvName() + "&vLength=" + total_len;
        Log.i("tv_playLog", "synPlayLog: -------------");
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
                            if (isComplete.equals("0")) {
                                finish();
                            }
                            Log.i("dj_tv", js.getString("msg"));
                        } else {
                            savePlayLog.dismiss();
                            showToast(msg);
                            finish();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });

    }

    private void closeVideo(int playlogtime, final String isComplete) {
        Log.e("closeVideo", playlogtime + "playlogtime: -------------" + isComplete);
        //正在保持
        savePlayLog = new ProgressDialog(this,
                R.style.MyProgressDialog);
        savePlayLog.setCancelable(false);
        savePlayLog.setMessage("正在保存播放记录...");
        savePlayLog.show();
        isSaving = true;
        String playlogTime = String.valueOf(playlogtime);
        String last = String.valueOf(playlogtime);
        String url = videoplayBean.getIpAddress() + "&vId=" + videoplayBean.getVideoId() + "&vType=" + videoplayBean.getVType() + "&course_id=" + videoplayBean.getCourseId() + "&templateId=" + videoplayBean.getMenuId() + "&menuName=" + videoplayBean.getMenuName()
                + "&player_time=" + playlogTime + "&menuId=" + videoplayBean.getMenuId() + "&lastPlayTimes=" + last + "&isComplete=" + isComplete + "&logPlayId=" + logPlayId + "&loginToken=" + videoplayBean.getLoginToken() + "&vName=" + videoplayBean.getvName() + "&vLength=" + total_len;
        Log.i("tv_playLog", "closeVideo: -------------");
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
                            savePlayLog.dismiss();
                            finish();
                            Log.i("dj_tv", js.getString("msg"));
                        } else {
                            savePlayLog.dismiss();
                            showToast(msg);
                            finish();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                savePlayLog.dismiss();
                finish();
            }
        });
    }


    private void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if (!isSaving) {
                closeVideo(saveHitTimes, isComplete);
            }
            return false;
        }
        return super.dispatchKeyEvent(event);
    }
}
