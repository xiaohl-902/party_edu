package com.igreatstone.partyedu.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Gravity;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.igreatstone.partyedu.view.VideoWindowSurfaceView;

import org.json.JSONException;
import org.json.JSONObject;

import static com.igreatstone.partyedu.MainActivity.framelayout;
import static com.igreatstone.partyedu.MainActivity.mainactivity;


/**
 * js调用的接口类
 * Created by yy on 2017/8/9.
 */

public class JsInterface {

    private Context context;
    private Handler mHandler;

    public static VideoWindowSurfaceView videoview = null;


    public JsInterface(Context context, Handler mHandler) {
        this.context = context;
        this.mHandler = mHandler;
    }

    /**
     * 获取系统apk的版本号
     *
     * @return
     */
    @JavascriptInterface
    public int getVersion() {
        return CommonUtil.getVersionCode(context);
    }

    /**
     * 关闭当前浏览器或应用界面
     *
     * @param reason
     * @return
     */
    @JavascriptInterface
    public void closeBrowser(String reason) {
        mHandler.post(new Runnable() {

            @Override
            public void run() {
            }
        });

    }


    @JavascriptInterface
    public void sendIntent(String mode, DataIntent dataIntent) {
        switch (mode) {

        }
    }

    /**
     * 启动apk
     *
     * @param n1
     * @param n2
     */
    @JavascriptInterface
    public void toActivity(final String n1, final String n2) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (n1 != null && n2 != null) {
                    Intent mintent = new Intent();
                    mintent.setComponent(new ComponentName(n1, n2));
                    context.startActivity(mintent);
                }
            }
        });
    }

    /**
     * 打开apk
     *
     * @param n2
     */
    @JavascriptInterface
    public void startApp(final String n2) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = context.getPackageManager().getLaunchIntentForPackage(n2);
                    context.startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(context, "没有安装", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @JavascriptInterface
    public void showInfoFromJs(final String url) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
//                String video_url = url;
//                Intent intent = new Intent(context, PlayerActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("playUrl", video_url);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
            }
        });
    }

    @JavascriptInterface
    public void playSmallVideo(final String jsonString) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    String videoUrl = jsonObject.optString("videoUrl");
                    if (videoUrl != null) {

                        mainactivity.removVideoView();

                        videoview = new VideoWindowSurfaceView(context, videoUrl);

                        FrameLayout.LayoutParams cameraFL = new FrameLayout.LayoutParams(
                                633, 396, Gravity.TOP); // set size

                        cameraFL.setMargins(324, 200, 323, 127); // set position
                        videoview.setLayoutParams(cameraFL);
                        framelayout.addView(videoview);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
