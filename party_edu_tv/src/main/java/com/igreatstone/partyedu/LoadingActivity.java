package com.igreatstone.partyedu;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import static com.igreatstone.partyedu.common.CommonUtil.isNetAvailable;

/**
 * Created by yy on 2017/11/7.
 */

public class LoadingActivity extends Activity {
    private TextView textView;
    private String mac = null;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        textView = (TextView) findViewById(R.id.text);


        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情
                handler.postDelayed(this, 3000);
                if (isNetAvailable(LoadingActivity.this)) {

//                    //hl 测试直播
//                    String video_url = "udp://@10.0.1.157:1234";
//                    Intent intent = new Intent(LoadingActivity.this, PlayLiveActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("playUrl", video_url);
//                    intent.putExtras(bundle);
//                    startActivity(intent);


                    handler.removeCallbacks(this);
                    Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        };
        handler.postDelayed(runnable, 3000);// 打开定时器，0s后执行runnable操作
        /*Intent intent = new Intent(LoadingActivity.this, PlayLiveActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("playUrl","http://39.104.85.73:8182/hls/TV00000000000000000001@HHZT.m3u8");
        startActivity(intent);*/
    }

}
