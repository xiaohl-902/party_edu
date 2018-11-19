package com.igreatstone.partyedu;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.igreatstone.partyedu.common.CustomVideoView;

/**
 * Created by yy on 2017/12/11.
 */

public class PlayLiveActivity extends Activity{
    private CustomVideoView vvPlayer;
    private ProgressBar progress;
    private String playUrl;

    private ProgressBar videoProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liveplay);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Bundle bundle = getIntent().getExtras();
        playUrl = bundle.getString("playUrl");// 播放网址
        vvPlayer = (CustomVideoView) findViewById(R.id.vvPlayer);

        videoProgressBar = (ProgressBar) this
                .findViewById(R.id.videoProgressBar);


        vvPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
            }
        });
        // 监听播放完成的事件�?
        vvPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });

        // 监听视频装载完成的事件�?
        vvPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                videoProgressBar.setVisibility(View.GONE);
            }
        });

        // 监听播放发生错误时�?的事件�?
        vvPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                videoProgressBar.setVisibility(View.GONE);
                return false;
            }
        });
        new Thread(readyPlay).start();
    }


    private Handler handlerMsg = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    if (playUrl != null && playUrl.length() > 0) {
                        vvPlayer.setVideoURI(Uri.parse(playUrl));
                        vvPlayer.start();
                    }
                    break;
            }
        }
    };

    Runnable readyPlay = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Message message = new Message();
            message.what = 100;
            handlerMsg.sendMessage(message);
        }
    };


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
}
