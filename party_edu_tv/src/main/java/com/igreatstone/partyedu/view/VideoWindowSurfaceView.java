package com.igreatstone.partyedu.view;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.igreatstone.partyedu.MainActivity;

import java.io.IOException;


/**
 * Created by zhuxi on 2017/6/26.
 */
public class VideoWindowSurfaceView extends SurfaceView implements SurfaceHolder.Callback, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnBufferingUpdateListener, View.OnClickListener {

    public static SurfaceHolder handleVedio = null;
    public static boolean isEnd = true;
    Context activity;
    public static int currentP = 0;
    //是否从起始位置开始播放
    public static boolean isPlayFromStart = false;
    public static boolean isPlayPause = false;
    MediaPlayer mediaPlayer;
    private Context context;
    private String videoPath;

    public VideoWindowSurfaceView(Context context, String videoPath) {
        super(context);
        this.context = context;
        this.videoPath = videoPath;
        handleVedio = this.getHolder();
        // 设置Holder类型,该类型表示surfaceView自己不管理缓存区,虽然提示过时，但最好还是要设置
        handleVedio.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        handleVedio.addCallback(this);
    }

    /**
     * 播放视频
     */
    public void playVideo() {
        Log.i("tv_launcher", "playVideo: -------------");
        isEnd = false;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            Log.i("tv_launcher", "playVideo mediaPlayer!=null");
        }
        // 初始化MediaPlayer
        mediaPlayer = new MediaPlayer();
        // 重置mediaPaly,建议在初始滑mediaplay立即调用。
        mediaPlayer.reset();
        // 设置声音效果
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // 设置播放完成监听
        mediaPlayer.setOnCompletionListener(this);
        // 设置媒体加载完成以后回调函数。
        mediaPlayer.setOnPreparedListener(this);
        // 错误监听回调函数
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(context, "加载视频错误！" + extra + what, Toast.LENGTH_SHORT).show();
                MainActivity.removLoadingView();
                return true;
            }
        });
        Uri uri = Uri.parse(videoPath);
        try {
            // mediaPlayer.reset();
            // mediaPlayer.setDataSource(pathString);
            mediaPlayer.setDataSource(context, uri);
            // mediaPlayer.setDataSource(SurfaceViewTestActivity.this, uri);
            // 设置异步加载视频，包括两种方式 prepare()同步，prepareAsync()异步
            mediaPlayer.prepareAsync();
//            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "加载视频错误！", Toast.LENGTH_SHORT).show();
            MainActivity.removLoadingView();
            Log.i("tv_launcher", "playVideo close from init error.");
        }
    }
//    public VideoWindowSurfaceView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public VideoWindowSurfaceView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("tv_launcher", "surfaceCreated window surfaceCreated....");
        setFocusable(false);// 设置键盘焦点
        setFocusableInTouchMode(false);// 设置触摸屏焦点
        playVideo();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("tv_launcher", "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        close();
    }

    public void close() {
        if (null != mediaPlayer) {
            currentP = mediaPlayer.getCurrentPosition();
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            isPlayPause = true;
        }
        isEnd = true;
    }

    public void pause() {
        if (null != mediaPlayer) {
            currentP = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
    }

    public void play() {
        if (null != mediaPlayer) {
            mediaPlayer.start();
            // 设置显示到屏幕
            mediaPlayer.setDisplay(handleVedio);
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mediaPlayer != null)
            mediaPlayer.start();
    }

    public void changeVedioSize(int width, int height) {
        ViewGroup.LayoutParams lp = this.getLayoutParams();
        lp.height = width;
        lp.width = height;
        this.setLayoutParams(lp);
    }

    public void setLoop(boolean isloop) {
        if (mediaPlayer != null)
            mediaPlayer.setLooping(isloop);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:

                Log.i("tv_launcher", "onError: MEDIA_ERROR_UNKNOWN");
//                Toast.makeText(context, "MEDIA_ERROR_UNKNOWN", Toast.LENGTH_SHORT)
//                        .show();
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Log.i("tv_launcher", "MEDIA_ERROR_SERVER_DIED");
//                Toast.makeText(context, "MEDIA_ERROR_SERVER_DIED",
//                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        switch (extra) {
            case MediaPlayer.MEDIA_ERROR_IO:
                Log.i("tv_launcher", "MEDIA_ERROR_IO");
//                Toast.makeText(context, "MEDIA_ERROR_IO", Toast.LENGTH_SHORT)
//                        .show();
                break;
            case MediaPlayer.MEDIA_ERROR_MALFORMED:
                Log.i("tv_launcher", "MEDIA_ERROR_MALFORMED");
//                Toast.makeText(context, "MEDIA_ERROR_MALFORMED",
//                        Toast.LENGTH_SHORT).show();
                break;
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Log.i("tv_launcher", "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK");
//                Toast.makeText(context,
//                        "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK",
//                        Toast.LENGTH_SHORT).show();
                break;
            case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                Log.i("tv_launcher", "MEDIA_ERROR_TIMED_OUT");
//                Toast.makeText(context, "MEDIA_ERROR_TIMED_OUT",
//                        Toast.LENGTH_SHORT).show();
                break;
            case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
                Log.i("tv_launcher", "MEDIA_ERROR_UNSUPPORTED");
//                Toast.makeText(context, "MEDIA_ERROR_UNSUPPORTED",
//                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        return false;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        if (mediaPlayer != null) {
            mediaPlayer.setOnPreparedListener(onPreparedListener);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.i("tv_launcher", "onPrepared isPlayPause:" + isPlayPause + "|" + currentP);
        // 播放视频
        if (isPlayFromStart) {
            mediaPlayer.seekTo(1);
            //马上置为false，因为视频开始播放以后，按home键切出去，再进来的话要从上次播放位置开始
            isPlayFromStart = false;
        } else {
            if (isPlayPause) {
                if (currentP >= mediaPlayer.getDuration()) {
                    currentP = 0;
                }
                mediaPlayer.seekTo(currentP);
            } else {
                mediaPlayer.seekTo(1);
            }
        }
        Log.i("tv_launcher", "onPrepared start ..");

        mediaPlayer.start();

        // 设置显示到屏幕
        mediaPlayer.setDisplay(handleVedio);
        // 设置surfaceView保持在屏幕上
        mediaPlayer.setScreenOnWhilePlaying(true);
        handleVedio.setKeepScreenOn(true);
        MainActivity.removLoadingView();
        Log.i("tv_launcher", "onPrepared end");
    }
}
