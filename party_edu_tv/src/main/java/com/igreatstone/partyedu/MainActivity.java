package com.igreatstone.partyedu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.StringUtils;
import com.igreatstone.partyedu.model.Config;
import com.igreatstone.partyedu.model.Terminal;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.igreatstone.partyedu.common.CommonUtil;
import com.igreatstone.partyedu.common.PreferenceUtil;
import com.igreatstone.partyedu.model.DataIntent;
import com.igreatstone.partyedu.model.LoginData;
import com.igreatstone.partyedu.model.PlayVideoBean;
import com.igreatstone.partyedu.model.PropName;
import com.igreatstone.partyedu.model.ApiUrl;
import com.igreatstone.partyedu.view.VideoWindowSurfaceView;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.igreatstone.partyedu.common.CommonUtil.updateVersion;

public class MainActivity extends Activity {
    private boolean isBack = false;
    //webview第一次加载页面
    private String firstLoadingUrl;
    //webview加载loading
    SpinKitView spinKitView;
    Sprite drawable = null;
    //小窗口播放加载loading
    public static SpinKitView smallLoading;
    public static Sprite smallDrawable;
    //web项目的首页
    private String webUrl;
    private WebView notfy2_webView;
    public static FrameLayout framelayout;
    public static boolean isInitEnd = false;
    public static boolean isPlay = false;
    private boolean startPlay = true;
    private Handler mHandler = new Handler();

    public static VideoWindowSurfaceView videoview = null;
    public static ProgressBar progressBar;
    public static MainActivity mainactivity;

    public static int sw = 1280;
    public static int sh = 720;

    private MainActivity mcontext;
    //    private String userName;
//    private String userPassword;
    private String userToken;
    private String loginData;
    private boolean isReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //必须调用初始化

        Intent intent = getIntent();
        //是否绑定页面调整
        isReg = intent.getBooleanExtra("isReg", false);
        if (isReg) {
            loginData = intent.getStringExtra("loginUrl");
            LoginData login = new Gson().fromJson(loginData, LoginData.class);
            String url = login.getDatas_androidWebURList().get(0).getUrl();
            //绑定了的用户之间从登陆页面获取loginToken
            userToken = PreferenceUtil.getString("loginToken", "");
            //第一次加载的url
            firstLoadingUrl = url;
            webUrl = url;

        } else {
            firstLoadingUrl = intent.getStringExtra("loadingUrl");
        }

        mcontext = this;
        setWindows();
        initView();
        initWebView();
    }

    /**
     * 检查更新
     */
    private void checkVersion() {
        ApiUrl apiUrl = App.getInstance().getApiUrl();
        Terminal terminal = App.getInstance().getTerminal();
        String checkUrl = apiUrl.getCheckVersion() + "&terminalType="
                + terminal.getType() + "&terminalTypeCode=" + terminal.getTypeCode() + "&terminalTypeName="
                + terminal.getTypeName() + "&terminalVersionCode="
                + CommonUtil.getVersionCode(mcontext) + "&loginToken=" + userToken;

        OkGo.<String>get(checkUrl).tag("this").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String checkData = response.body();
                if (!TextUtils.isEmpty(checkData)) {

                    try {
                        JSONObject json = new JSONObject(checkData);
                        int status = json.optInt("status");
                        if (status == 1) {
                            String data = json.optString("datas");
                            JSONObject jsondata = new JSONObject(data);
                            final String download_url = jsondata.getString("downloadUrl");
                            if (!StringUtils.isEmpty(download_url)) {
                                Runnable runDownload = new Runnable() {
                                    @Override
                                    public void run() {
                                        if (download_url != null) {
                                            try {
                                                updateVersion(download_url, mcontext);
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });

    }

    /**
     * 初始化窗口
     */
    private void setWindows() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // 在activity和fragment中加载SurfaceView，屏幕会闪一下(黑色)
        // 解决办法：在activity的oncreate方法中加入getWindow().setFormat(PixelFormat.TRANSLUCENT);
        // 需要导入包import android.graphics.PixelFormat;
        // 在fragment中使用在getWindow前加getActivity()
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }


    private void initView() {
        Log.i("tv_launcher", "initView: ");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        sw = dm.widthPixels;
        sh = dm.heightPixels;
        int densityDpi = dm.densityDpi;
        Log.i("tv_launcher", "initView: densityDpi:" + densityDpi);
        notfy2_webView = new WebView(mcontext);
        notfy2_webView.setVisibility(View.VISIBLE);
        notfy2_webView.requestFocus();
        ViewGroup.LayoutParams framelayout_params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        framelayout = new FrameLayout(this);
        framelayout.setBackgroundColor(Color.parseColor("#000000"));
        framelayout.setLayoutParams(framelayout_params);

        isInitEnd = false;
        isPlay = true;
    }

    /**
     * 初始化 webView
     */
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void initWebView() {
        Log.i("tv_launcher", "initWebView: ");
        notfy2_webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        notfy2_webView.setBackgroundColor(Color.parseColor("#000000"));
        WebSettings settings = notfy2_webView.getSettings();
        notfy2_webView.getSettings().setDomStorageEnabled(true);
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

        notfy2_webView.addJavascriptInterface(new JsInterface(), "nativeMethod");

        notfy2_webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                notfy2_webView.requestFocus();
                Log.i("tv_launcher", "网页加载中");
                spinKitView.setVisibility(View.VISIBLE);
                drawable.start();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                notfy2_webView.requestFocus();
                Log.i("tv_launcher", "网页加载结束");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        notfy2_webView.setVisibility(View.VISIBLE);
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

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }
        });

        notfy2_webView.setWebChromeClient(
//        notfy2_webView.setWebChromeClient(new MyWebChromeClient());
                new WebChromeClient() {

                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        if (newProgress < 1) { // 加载中
                            Log.i("tv_launcher", "网页加载进度：" + newProgress);
                            notfy2_webView.requestFocus();
                        } else if (newProgress == 100) { // 网页加载完成
                            Log.i("tv_launcher", "网页加载进度：" + newProgress);
                            notfy2_webView.requestFocus();
                        }
                        super.onProgressChanged(view, newProgress);
                    }


                });


        float scanW = (float) sw / (float) 1280;
        float scanH = (float) sh / (float) 720;

        notfy2_webView.setInitialScale((int) (scanW * 100));
        framelayout.addView(notfy2_webView);
        setContentView(framelayout);

        spinKitView = new SpinKitView(mcontext);

        FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER); // set size
        spinKitView.setLayoutParams(frameParams);
        framelayout.addView(spinKitView);

        drawable = new Circle();
        drawable.setBounds(0, 0, 200, 200);
        drawable.setColor(Color.WHITE);
        spinKitView.setIndeterminateDrawable(drawable);

        if (!TextUtils.isEmpty(firstLoadingUrl)) {
            notfy2_webView.loadUrl(firstLoadingUrl);
        }
        if (!TextUtils.isEmpty(userToken)) {
            checkVersion();
        }

    }


    /**
     * 移除小窗口loading
     */
    public static void removLoadingView() {
        if (smallLoading != null && smallDrawable != null) {
            smallLoading.setVisibility(View.INVISIBLE);
            smallDrawable.stop();
        }

    }

    public static void removVideoView() {
//        if (laodingView != null) {
//            framelayout.removeView(laodingView);
//            laodingView = null;
//        }
        if (videoview != null) {
            framelayout.removeView(videoview);
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        notfy2_webView.loadUrl(notfy2_webView.getUrl());
        notfy2_webView.setVisibility(View.VISIBLE);
        notfy2_webView.setFocusable(true);
        notfy2_webView.requestFocus();
    }

    /**
     * 解决退出仍有声音的bug
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 继续播放：在页面的onResume方法中使用
     */

    @Override
    protected void onResume() {
        super.onResume();
        if (startPlay) {
            startPlay = false;
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("tv_launcher", "Notify2Activity onDestroy: ");
        System.gc();
        if (videoview != null) {
            videoview.close();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if(notfy2_webView.canGoBack()){
                removVideoView();
                removLoadingView();
                notfy2_webView.goBack();   //后退
                return true;
            }else{finish();}
        }
        return super.dispatchKeyEvent(event);
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (notfy2_webView.getUrl().equals(webUrl)) {
//                finish();
//            }
//        }
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && notfy2_webView.canGoBack()) {
//            removVideoView();
//            removLoadingView();
//            notfy2_webView.goBack();   //后退
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }

    /**
     * webview和js的交互类
     */
    public class JsInterface {
        @JavascriptInterface
        public void playVideo(String jsonString) {
            getVideoData(jsonString);
            notfy2_webView.requestFocus();
        }

        /**
         * 启动apk
         *
         * @param mode
         * @param intent
         */
        @JavascriptInterface
        public void sendIntent(String mode, final String intent) {
            getIntentData(mode, intent);
        }


        @JavascriptInterface
        public void stopVideo() {

            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    if (videoview != null) {
                        removLoadingView();
                        removVideoView();
                        videoview.close();
                    }
                }
            });
        }

        @JavascriptInterface
        public void setHandler() {

        }

        @JavascriptInterface
        public void goLastUrl() {
            notfy2_webView.goBack();
        }

        @JavascriptInterface
        public void getLoginData(String loginData) {
            if (!TextUtils.isEmpty(loginData)) {
                loginData = loginData;
                LoginData login = new Gson().fromJson(loginData, LoginData.class);
                webUrl = login.getDatas_androidWebURList().get(0).getUrl();
                userToken = login.getDatas().get(0).getLoginToken();
            }
        }

        /**
         * 省统一登录的数据：首次登录设置登录data
         * 解决如果登录ip和登录成功跳转首页ip不相同 则无法实现html5本地存储导致无栏目问题
         * @param loginData
         */
        @JavascriptInterface
        public void setLoginData(String loginData) {
            if (!TextUtils.isEmpty(loginData)) {
                loginData = loginData;
                LoginData login = new Gson().fromJson(loginData, LoginData.class);
                webUrl = login.getDatas_androidWebURList().get(0).getUrl();
                userToken = login.getDatas().get(0).getLoginToken();
                PreferenceUtil.put("loginData", loginData);
                PreferenceUtil.put("userToken", userToken);
                PreferenceUtil.put("webUrl", webUrl);
            }
        }

        /**
         * 读取登录信息
         */
        @JavascriptInterface
        public String readLoginData() {
            if (!TextUtils.isEmpty(loginData)) {
                return loginData;
            }
            return PreferenceUtil.getString("loginData", "");//省统一登录的数据
        }


        /**
         * 视频数据
         */
        @JavascriptInterface
        public void videoData(String videoData) {
            if (!TextUtils.isEmpty(videoData)) {
                Intent intent = new Intent(MainActivity.this,
                        VideoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("videoData", videoData);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }


        /**
         * 打开页面
         */
        @JavascriptInterface
        public void openInternet(String webUrl) {
            if (!TextUtils.isEmpty(webUrl)) {
                Intent intent = new Intent(MainActivity.this,
                        WebActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("webUrl", webUrl);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }


        /**
         *
         */
        @JavascriptInterface
        public void liveVideo(String videoData) {
            if (!TextUtils.isEmpty(videoData)) {
                Intent intent = new Intent(MainActivity.this,
                        PlayLiveActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("playUrl", videoData);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }

        @JavascriptInterface
        public void liveVideo2(String videoData) {
            if (!TextUtils.isEmpty(videoData)) {
                Intent intent = new Intent(MainActivity.this,
                        PlayLiveActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putString("playUrl", videoData);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }


        /**
         * 获取系统参数
         */
        @JavascriptInterface
        public String readSystemProp() {
            String propNameString = null;
            String Mac = CommonUtil.getMac();
            int appVersion = CommonUtil.getVersionCode(mcontext);
            PackageManager pm = mcontext.getPackageManager();
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

    }

    private void getIntentData(String mode, final String intent) {
        switch (mode) {
            case "sendBroadcast":
                break;
            case "startActivity":
                Observable.unsafeCreate(new Observable.OnSubscribe<DataIntent>() {
                    @Override
                    public void call(Subscriber<? super DataIntent> subscriber) {
                        DataIntent videoBean = new Gson().fromJson(intent, DataIntent.class);
                        subscriber.onNext(videoBean);
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<DataIntent>() {
                            @Override
                            public void onCompleted() {
                                this.unsubscribe();
                            }

                            @Override
                            public void onError(Throwable e) {
                                this.unsubscribe();
                            }

                            @Override
                            public void onNext(DataIntent dataIntent) {
                                if (!TextUtils.isEmpty(dataIntent.toString())) {
                                    Intent mintent = new Intent();
                                    mintent.setComponent(new ComponentName(dataIntent.getPackageX(), dataIntent.getActivity()));
                                    Bundle bundle = new Bundle();
                                    bundle.putString("key0", dataIntent.getExtras().getKey0());
                                    mintent.putExtras(bundle);
                                    startActivity(mintent);
                                }
                            }
                        });

                break;
            case "startService":
                break;
        }
    }

    /**
     * 获取小视频
     *
     * @param jsonString
     */
    private void getVideoData(final String jsonString) {
        Observable.unsafeCreate(new Observable.OnSubscribe<PlayVideoBean>() {
            @Override
            public void call(Subscriber<? super PlayVideoBean> subscriber) {
                PlayVideoBean videoBean = new Gson().fromJson(jsonString, PlayVideoBean.class);
                subscriber.onNext(videoBean);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PlayVideoBean>() {
                    @Override
                    public void onCompleted() {
                        this.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        this.unsubscribe();
                    }

                    @Override
                    public void onNext(PlayVideoBean playVideoBean) {
                        setVideo(playVideoBean);
                    }
                });

    }

    /**
     * 小视频设置
     *
     * @param playVideoBean
     */
    private void setVideo(PlayVideoBean playVideoBean) {

        if (!TextUtils.isEmpty(playVideoBean.getPlay_url())) {

            removVideoView();
            videoview = new VideoWindowSurfaceView(mcontext, playVideoBean.getPlay_url());
            float scanW = (float) sw / (float) 1280;

            int x = (int) (Integer.valueOf(playVideoBean.getMarginLeft()) * scanW);
            int y = (int) (Integer.valueOf(playVideoBean.getMarginTop()) * scanW);
            int w = (int) (Integer.valueOf(playVideoBean.getWidth()) * scanW);
            int h = (int) (Integer.valueOf(playVideoBean.getHeight()) * scanW);

            FrameLayout.LayoutParams cameraFL = new FrameLayout.LayoutParams(
                    w, h, Gravity.TOP); // set size

            cameraFL.setMargins(x, y, 0, 0); // set position
            videoview.setLayoutParams(cameraFL);
            framelayout.addView(videoview);


//            smallLoading = new SpinKitView(mcontext);
//
//
//            FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(
//                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER); // set size
//            smallLoading.setLayoutParams(frameParams);
//            framelayout.bringChildToFront(smallLoading);
//            framelayout.addView(smallLoading);
//            smallDrawable = new Circle();
//            smallDrawable.setBounds(0, 0, 200, 200);
//            smallDrawable.setColor(Color.WHITE);
//            smallLoading.setIndeterminateDrawable(smallDrawable);

        }
    }

}
