package com.igreatstone.partyconst;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.StringUtils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.gson.Gson;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.blankj.utilcode.util.Utils.getApp;

public class MainActivity extends Activity {
    SpinKitView spinKitView;
    Sprite drawable = null;
    private String buildWebUrl = BuildConfig.WEB_URL;

    private WebView webView;
    private String v_url;
    private CustomVideoView customVideoView;
    private RelativeLayout.LayoutParams params;
    Handler mHandler = new Handler();

    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        Intent intent = getIntent();
        String webUrl = intent.getStringExtra("webUrl");
        webUrl = StringUtils.isEmpty(webUrl) ? buildWebUrl : webUrl;

        if (!StringUtils.isEmpty(webUrl)) {
            webView.loadUrl(webUrl);
        }

        //安装apk
        /*final Map<String,String> apps = new HashMap<>();
        apps.put("com.igreatstone.partyedu.sc", "dangjiao");//党教

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String pkg : apps.keySet()) {
                    if (!AppUtils.isAppInstalled(pkg)) {
                        install(mContext, apps.get(pkg));
                    }
                }
            }
        }).start();*/


    }


    /**
     * 初始化webview
     */
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void init() {
        webView = (WebView) findViewById(R.id.wvContent);
        spinKitView = (SpinKitView) findViewById(R.id.spin_kit);
        drawable = new Circle();
        drawable.setBounds(0, 0, 200, 200);
        drawable.setColor(Color.WHITE);
        spinKitView.setIndeterminateDrawable(drawable);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebChromeClient(new WebChromeClient() {
            /**
             * 显示自定义视图，无此方法视频不能播放
             */
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);
            }
            /*
             * private void onPageFinished(WebView view, String url) { // TODO
             * Auto-generated method stub webView.loadUrl(
             * "javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}}"
             * ); }
             */
        });
        webView.getSettings().setBlockNetworkImage(false);// 解决图片不显示
        // 在js中调用本地java方法

        webView.addJavascriptInterface(new JsInterface(), "nativeMethod");
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                spinKitView.setVisibility(View.VISIBLE);
                drawable.start();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                webView.setFocusable(true);
                webView.requestFocus();
                spinKitView.setVisibility(View.INVISIBLE);
                drawable.stop();
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });

        //跨域请求问题
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                Class<?> clazz = webView.getSettings().getClass();
                Method method = clazz.getMethod(
                        "setAllowUniversalAccessFromFileURLs", boolean.class);//利用反射机制去修改设置对象
                if (method != null) {
                    method.invoke(webView.getSettings(), true);//修改设置
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(webView != null){
            webView.loadUrl("javascript:onResume()");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(webView != null){
            webView.loadUrl("javascript:onPause()");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        webView.setFocusable(true);
        webView.requestFocus();
        if(webView != null){
            webView.loadUrl("javascript:onRestart()");
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebBackForwardList mWebBackForwardList = webView.copyBackForwardList();
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            customVideoView = (CustomVideoView) findViewById(R.id.vvPlayer);
            customVideoView.setFocusable(false);
            params = (RelativeLayout.LayoutParams) customVideoView.getLayoutParams();
            params.width = 0;
            params.height = 0;
            customVideoView.setLayoutParams(params);
            customVideoView.getHolder().setFixedSize(params.width, params.height);
            customVideoView.stopPlayback();
            webView.setFocusable(true);
            webView.requestFocus();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class JsInterface {

        private Context mContext;

        @JavascriptInterface
        public String readSystemProp() {
            String propNameString = null;
            String Mac = CommonUtil.getMac();
            int appVersion = CommonUtil.getVersionCode(MainActivity.this);
            PackageManager pm = MainActivity.this.getPackageManager();
            String appName = getApplicationInfo().loadLabel(pm).toString();
            PropName propName = new PropName();
            propName.setAppName(appName);
            propName.setAppVersion(String.valueOf(appVersion));
            propName.setMac(Mac);
            if (Mac != null && appName != null && appVersion > 0) {
                Gson gson = new Gson();
                propNameString = gson.toJson(propName);
                return propNameString;
            }
            return propNameString;
        }

        @JavascriptInterface
        public String getBrowserPosition() {
//            Map<String, Integer> map = new HashMap<String, Integer>();
            webView = (WebView) findViewById(R.id.wvContent);
            Integer x = webView.getLeft();
            Integer y = webView.getTop();
            Integer width = webView.getWidth();
            Integer height = webView.getHeight();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("x", x);
                jsonObject.put("y", y);
                jsonObject.put("width", width);
                jsonObject.put("height", height);
                return jsonObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject.toString();
        }

        /**
         * 关闭当前浏览器或应用界面
         *
         * @param reason
         * @return
         */
        @JavascriptInterface
        public void closeBrowser(String reason) {
            finish();
        }

        /**
         * 调整浏览器尺寸
         *
         * @param width
         * @param height
         */
        @JavascriptInterface
        public void resizeBrowser(int width, int height) {
            webView = (WebView) findViewById(R.id.wvContent);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) webView.getLayoutParams();
            params.width = width;
            params.height = height;
            webView.setLayoutParams(params);
            webView.requestFocus();
        }

        /**
         * 设置播放器的位置
         *
         * @param x
         * @param y
         * @param width
         * @param height
         */
        @JavascriptInterface
        public void setVideoWindowPosition(final int x, final int y, final int width, final int height) {

        }

        @JavascriptInterface
        public void playSmallVideo(final String jsonString) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonString);
                        String videoUrl = jsonObject.optString("videoUrl");
                        String width = jsonObject.optString("width");
                        String height = jsonObject.optString("height");
                        String marginLeft = jsonObject.optString("marginLeft");
                        String marginTop = jsonObject.optString("marginTop");
                        if (videoUrl != null) {
                            customVideoView = (CustomVideoView) findViewById(R.id.vvPlayer);
                            customVideoView.setFocusable(false);
                            params = (RelativeLayout.LayoutParams) customVideoView.getLayoutParams();
                            params.width = !TextUtils.isEmpty(width)?Integer.parseInt(width): 633;
                            params.height = !TextUtils.isEmpty(height)?Integer.parseInt(height): 396;
                            //params.setMargins(324, 200, 323, 127);
                            int left = !TextUtils.isEmpty(marginLeft)?Integer.parseInt(marginLeft):324;
                            int top = !TextUtils.isEmpty(marginTop)?Integer.parseInt(marginTop):200;
                            params.setMargins(left, top, 0, 0);
                            customVideoView.setLayoutParams(params);
                            customVideoView.setVideoURI(Uri.parse(videoUrl));
                            customVideoView.start();
                            customVideoView.getHolder().setFixedSize(params.width, params.height);
                            webView.requestFocus();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @JavascriptInterface
        public void stopSmallVidplaySmallVideoeo() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    customVideoView = (CustomVideoView) findViewById(R.id.vvPlayer);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) customVideoView.getLayoutParams();
                    params.width = 0;
                    params.height = 0;
                    customVideoView.setLayoutParams(params);
                    customVideoView.stopPlayback();
                }
            });
        }


        @JavascriptInterface
        public void showInfoFromJs(final String url) {

            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    String video_url = url;
                    Intent intent = new Intent(MainActivity.this,
                            PlayerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("playUrl", video_url);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            MainActivity.this.v_url = url;
        }

        @JavascriptInterface
        public void openUrl(final String url, final String options) {
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(MainActivity.this,
                            WebViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("webUrl", url);
                    if(!TextUtils.isEmpty(options)){
                        Map<String,String> params = new Gson().fromJson(options,Map.class);
                        if(params != null){
                            for (String key : params.keySet()) {
                                bundle.putString(key, params.get(key));
                            }
                        }
                        //bundle.putString("proxyIp", proxyIp);
                        //bundle.putString("proxyPort", proxyPort);
                        //bundle.putString("encodingName", encodingName);
                    }
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        @JavascriptInterface
        public void launchApp(String pkg) {
            AppUtils.launchApp(pkg);
        }

        @JavascriptInterface
        public void launchApp(String pkg, String cls) {
            ActivityUtils.startActivity(pkg, cls);
        }

        @JavascriptInterface
        public void launchApp(String pkg, String cls, String options) {
            if (!TextUtils.isEmpty(options)) {
                Bundle bundle = new Bundle();
                Gson gson = new Gson();
                Map<String, String> params = gson.fromJson(options, Map.class);
                for (String key : params.keySet()) {
                    bundle.putString(key, params.get(key));
                }
                ActivityUtils.startActivity(pkg, cls, bundle);
            } else {
                ActivityUtils.startActivity(pkg, cls);
            }
        }

        @JavascriptInterface
        public boolean isAppInstalled(String pkg) {
            return AppUtils.isAppInstalled(pkg);
        }


        /**
         * 检查版本
         */
        @JavascriptInterface
        private void checkVersion(final String downloadUrl) {
            if (!StringUtils.isEmpty(downloadUrl)) {
                Runnable runDownload = new Runnable() {
                    @Override
                    public void run() {
                        if (downloadUrl != null) {
                            try {
                                CommonUtil.updateVersion(downloadUrl, MainActivity.this);
                            } catch (Exception e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }
                        }
                    }
                };
                new Thread(runDownload).start();
            }
        }

    }

    //安装apk内apk
    private void install(Context context, String apkName) {
        try {
            //建立apk路径
            String path = context.getCacheDir().getAbsoluteFile() + "/" + apkName + ".apk";
            File file = new File(path);
            //判断文件路径是否存，不存在就新建一个。
            if (!file.exists()) {
                file.createNewFile();
            }
            //获取本地的*.tmp
            InputStream is = context.getClass().getClassLoader().getResourceAsStream("assets/" + apkName + ".tmp");
            //将*.tmp的内容写入到*.apk中。
            FileIOUtils.writeFileFromIS(file, is);
            //提升路径
            String cmd = "chmod 777 " + file.getAbsolutePath();
            Runtime.getRuntime().exec(cmd);
            cmd = "chmod 777 " + file.getParent();
            Runtime.getRuntime().exec(cmd);
            cmd = "chmod 777" + new File(file.getParent()).getParent();
            Runtime.getRuntime().exec(cmd);
            //开始安装
            AppUtils.installApp(file);
            /*
            if(DeviceUtils.isDeviceRooted()){
                AppUtils.installAppSilent(file, "", true);
            }else {
                AppUtils.installApp(file);
            }*/

        } catch (Exception e) {
            Log.d("错误：", e.getMessage());
        }
    }


}
