package com.igreatstone.partyedu;

/**
 * Created by yy on 2017/12/26.
 * <p>
 * 给js调用的方法接口
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;


public class JsInterface {

    private Context context;
    private MainActivityX5 activity;

    public JsInterface(Context context, MainActivityX5 activity) {
        this.context = context;
        this.activity = activity;
    }


    @JavascriptInterface
    public void showInfoFromJs(final String url) {
        String video_url = url;
        Intent intent = new Intent(activity,
                PlayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("playUrl", video_url);
        intent.putExtras(bundle);
        activity.startActivity(intent);

    }


    @JavascriptInterface
    public void showInfoFromJsLive(final String url) {
                String video_url = url;
                Intent intent = new Intent(activity,
                        PlayLiveActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("playUrl", video_url);
                intent.putExtras(bundle);
                activity.startActivity(intent);
    }


    /**
     *
     */
    @JavascriptInterface
    public void videoData(String videoData) {
        if (!TextUtils.isEmpty(videoData)) {
            Intent intent = new Intent(activity,
                    PlayActivity1.class);
            Bundle bundle = new Bundle();
            bundle.putString("videoData", videoData);
            intent.putExtras(bundle);
            activity.startActivity(intent);
        }
    }
}
