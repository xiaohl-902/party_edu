package com.igreatstone.partyedu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebBackForwardList;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.igreatstone.partyedu.common.PreferenceUtil;
import com.igreatstone.partyedu.common.ProxySettings;
import com.igreatstone.partyedu.common.ToastUtil;

/**
 * Created by yy on 2017/12/8.
 */

public class WebActivity extends Activity {

    //webview加载loading
    SpinKitView spinKitView;
    Sprite drawable = null;
    private String web_url;
    public static int sw = 1280;
    public static int sh = 720;
    private WebView webview;
    public static FrameLayout framelayout;
    private String agentPort, agentIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        web_url = intent.getStringExtra("webUrl");
        agentIp = PreferenceUtil.getString("agentIp", "");
        agentPort = PreferenceUtil.getString("agentPort", "");

        /*if (agentIp.equals("") && agentPort.equals("")) {
            ToastUtil.showLong(this, "代理服务器IP和端口为空");
        }*/

        initView();
        initWebView();
    }

    /**
     * 初始化 webView
     */
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void initWebView() {
        webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webview.setBackgroundColor(Color.parseColor("#000000"));
        WebSettings settings = webview.getSettings();
        webview.getSettings().setDomStorageEnabled(true);
        // 支持js
        settings.setJavaScriptEnabled(true);
        // 设置字符编码
//        settings.setDefaultTextEncodingName("GBK");
        settings.setDefaultTextEncodingName("UTF-8");// 设置字符编码
        // 启用支持javascript
        settings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        settings.setBuiltInZoomControls(true);
        settings.setLightTouchEnabled(true);
        settings.setSupportZoom(true);
        // 不使用缓存，只从网络获取数据.
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 支持JS交互
        settings.setJavaScriptCanOpenWindowsAutomatically(true);


        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                webview.requestFocus();
                Log.i("tv_launcher", "网页加载中");
                webview.setVisibility(View.VISIBLE);
                drawable.start();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webview.requestFocus();
                Log.i("tv_launcher", "网页加载结束");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        webview.setVisibility(View.VISIBLE);
                        spinKitView.setVisibility(View.INVISIBLE);
                        drawable.stop();
                    }
                }, 0);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("tv_launcher", "shouldOverrideUrlLoading: url" + url);
                view.loadUrl(url);//在2.3上面不加这句话，可以加载出页面，在4.0上面必须要加入，不然出现白屏
                return true;
            }
        });

        if (agentIp.equals("") && agentPort.equals("")) {
            //ToastUtil.showLong(this, "代理服务器IP和端口为空");
            if (web_url != null) {
                webview.loadUrl(web_url);
            }
        } else {
            ProxySettings.setProxy(webview, agentIp, Integer.parseInt(agentPort), null);
            if (web_url != null) {
                webview.loadUrl(web_url);
            }
        }
    }

    private void initView() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        sw = dm.widthPixels;
        sh = dm.heightPixels;
        int densityDpi = dm.densityDpi;
        webview = new WebView(this);
        webview.setVisibility(View.VISIBLE);
        webview.requestFocus();
        ViewGroup.LayoutParams framelayout_params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        framelayout = new FrameLayout(this);
        framelayout.setBackgroundColor(Color.parseColor("#000000"));
        framelayout.setLayoutParams(framelayout_params);


        float scanW = (float) sw / (float) 1280;
        float scanH = (float) sh / (float) 720;

        webview.setInitialScale((int) (scanW * 100));
        framelayout.addView(webview);
        setContentView(framelayout);
        spinKitView = new SpinKitView(this);
        FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER); // set size
        spinKitView.setLayoutParams(frameParams);
        framelayout.addView(spinKitView);
        drawable = new Circle();
        drawable.setBounds(0, 0, 200, 200);
        drawable.setColor(Color.WHITE);
        spinKitView.setIndeterminateDrawable(drawable);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebBackForwardList mWebBackForwardList = webview.copyBackForwardList();
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (!TextUtils.isEmpty(web_url) && web_url.equals(webview.getUrl())) {
                finish();
            }
            if (webview.canGoBack()) {
                webview.goBack();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
