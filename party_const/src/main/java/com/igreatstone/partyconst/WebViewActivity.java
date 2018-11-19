package com.igreatstone.partyconst;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebBackForwardList;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yy on 2017/10/10.
 */

public class WebViewActivity extends Activity {

    private WebView wvContent;
    String webUrl;
    String proxyIp;
    String proxyPort;
    String encodingName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        webUrl = intent.getStringExtra("webUrl");
        proxyIp = intent.getStringExtra("proxyIp");
        proxyPort = intent.getStringExtra("proxyPort");
        encodingName = intent.getStringExtra("encodingName");

        wvContent = (WebView) findViewById(R.id.wvContent);
        wvContent.getSettings().setJavaScriptEnabled(true);

        wvContent.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wvContent.getSettings().setLoadWithOverviewMode(true);
        wvContent.getSettings().setSupportZoom(true);
        // 保存表单数据
        wvContent.getSettings().setSaveFormData(true);
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        wvContent.getSettings().setSupportZoom(true);
        wvContent.getSettings().setBuiltInZoomControls(true);
        wvContent.getSettings().setDisplayZoomControls(false);
        // 启动应用缓存
        wvContent.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        wvContent.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // setDefaultZoom api19被弃用
        // 设置此属性，可任意比例缩放。
        wvContent.getSettings().setUseWideViewPort(true);
        // 缩放比例 1
        wvContent.setInitialScale(1);
        // 告诉WebView启用JavaScript执行。默认的是false。
        wvContent.getSettings().setJavaScriptEnabled(true);
        // 页面加载好以后，再放开图片
        wvContent.getSettings().setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        wvContent.getSettings().setDomStorageEnabled(true);
        // 排版适应屏幕
        wvContent.getSettings().setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        wvContent.addJavascriptInterface(new InJavaScriptLocalObj(),
                "local_obj");
        wvContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("url:" + url);
                if (url.endsWith(".wmv") || url.endsWith(".mp4")
                        || url.endsWith(".avi")) {
                    // Toast.makeText(getApplicationContext(), url,
                    // Toast.LENGTH_SHORT).show();
                    playWMV(url);
                } else {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:window.local_obj.showSource('<head>'+"
                        + "document.getElementsByTagName('html')[0].innerHTML+'</head>');");

            }
        });

        if(!TextUtils.isEmpty(encodingName)){
            wvContent.getSettings().setDefaultTextEncodingName(encodingName);
        }

        if (!TextUtils.isEmpty(proxyIp) && !TextUtils.isEmpty(proxyPort)) {
            int port = Integer.parseInt(proxyPort);
            ProxySettings.setProxy(wvContent, proxyIp, port, null);
        }

        if (!TextUtils.isEmpty(webUrl)) {
            wvContent.loadUrl(webUrl);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebBackForwardList mWebBackForwardList = wvContent
                .copyBackForwardList();
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (!TextUtils.isEmpty(webUrl) && webUrl.equals(wvContent.getUrl())) {
                finish();
            }
            if (wvContent.canGoBack()) {
                wvContent.goBack();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);

    }

    private void playWMV(String str_wmv_url) {
        ComponentName componetName = new ComponentName(
                "com.hisilicon.android.videoplayer",
                "com.hisilicon.android.videoplayer.activity.VideoActivity");

        try {
            Intent intent = new Intent();
            intent.setComponent(componetName);
            intent.setData(Uri.parse(str_wmv_url));
            startActivity(intent);
        } catch (Exception e) {
        }
    }

    private String getApiUrl() {
        //return CommonUtil.getApiUrl(this);
        return"";
    }

    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            System.out.println("====>html=" + html);
            if (html != null) {
                Pattern pattern = Pattern.compile("href=\"(.{1,300}wmv)\">");
                Matcher matcher = pattern.matcher(html);

                if (matcher.find()) {
                    // Toast.makeText(getApplicationContext(), matcher.group(1),
                    // Toast.LENGTH_SHORT).show();
                    // System.out.println("--------------wmv:"+matcher.group(1));

                    wvContent
                            .loadUrl("javascript:document.write('<style>body {margin:0px;padding:0px;}</style><div style=\"background-image:url("
                                    + getApiUrl()
                                    + "web_play_img/web_play_bg.jpg);width:100%; height:100%\"><br><br><br><br><br><br><br><br><br><br><br><br><center><a href=\""
                                    + matcher.group(1)
                                    + "\" id=\"link_video_play\"><img src=\""
                                    + getApiUrl()
                                    + "web_play_img/btn_web_play.png\"/></a></center></div>');");
                    // wvContent.loadUrl("javascript:alert(document.getElementById('link_video_play')); ");

                    // wvContent.loadUrl("javascript:window.location.href.write('<a href=\""+matcher.group(1)+"\" id=\"link_video_play\">播放视频</a>');");
                }
            }
        }
    }
}
