package com.igreatstone.partyedu;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.webkit.ValueCallback;
import android.widget.TextView;

import com.igreatstone.partyedu.utils.X5WebView;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivityX5 extends Activity {


    private Dialog dialog;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private boolean isContinue = false;
    private ValueCallback<Uri> mUploadMessage;// 表单的数据信息
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private final static int FILECHOOSER_RESULTCODE = 1;// 表单的结果回调</span>
    private TextView mTvCenterBadnet;
    HProgressBarLoading mTopProgress;
    private X5WebView x5WebView;
    //xwalkView加載的url
    private String web_url;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainx5);
//        StatusBarCompat.setStatusBarColor(this, R.color.colorPrimary);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        init();

    }

    private void init() {
        x5WebView=(X5WebView)findViewById(R.id.x5webView);

        //x5WebView.loadUrl("http://124.232.153.82:8015/cadmin_scn/");
        x5WebView.loadUrl(BuildConfig.LOGIN_URL);

        mTopProgress = (HProgressBarLoading) findViewById(R.id.top_progress);
        mTvCenterBadnet = (TextView) findViewById(R.id.tv_center_badnet);
        x5WebView.addJavascriptInterface(new JsInterface(MainActivityX5.this,this),"nativeMethod");
        verifyStoragePermissions(this);
        x5WebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                errorOperation();
            }
        });
        x5WebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);

                // 如果没有网络直接跳出方法
                if (!UiUtil.isNetworkAvailable(MainActivityX5.this)) {
                    return;
                }
                // 如果进度条隐藏则让它显示
                if (View.INVISIBLE == mTopProgress.getVisibility()) {
                    mTopProgress.setVisibility(View.VISIBLE);
                    // TVLoading.setVisibility(View.VISIBLE);
                }
                // 大于80的进度的时候,放慢速度加载,否则交给自己加载
                if (i >= 80) {
                    // 拦截webView自己的处理方式
                    if (isContinue) {
                        return;
                    }
                    mTopProgress.setCurProgress(100, 3000,
                            new HProgressBarLoading.OnEndListener() {
                                @Override
                                public void onEnd() {
                                    finishOperation(true);
                                    isContinue = false;
                                }
                            });

                    isContinue = true;
                } else {
                    mTopProgress.setNormalProgress(i);
                }
            }


            // For Android 3.0+
            public void openFileChooser(com.tencent.smtt.sdk.ValueCallback<Uri> uploadMsg, String acceptType) {
                Log.i("test", "openFileChooser 1");
                mUploadMessage = uploadMsg;
                take();
            }

            // For Android < 3.0
            public void openFileChooser(com.tencent.smtt.sdk.ValueCallback<Uri> uploadMsgs) {
                Log.i("test", "openFileChooser 2");
                mUploadMessage = uploadMsgs;
                take();
            }

            // For Android  > 4.1.1
            public void openFileChooser(com.tencent.smtt.sdk.ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                Log.i("test", "openFileChooser 3");
                mUploadMessage = uploadMsg;
                take();
            }

            // For Android  >= 5.0
            public boolean onShowFileChooser(com.tencent.smtt.sdk.WebView webView,
                                             com.tencent.smtt.sdk.ValueCallback<Uri[]> filePathCallback,
                                             WebChromeClient.FileChooserParams fileChooserParams) {
                Log.i("test", "openFileChooser 4:" + filePathCallback.toString());
                mUploadCallbackAboveL = filePathCallback;
                take();
                return true;
            }
        });




    }



    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if (x5WebView.canGoBack()) {
                //                xWalkView.getNavigationHistory().navigate(XWalkNavigationHistory.Direction.BACKWARD, 1);
                if(x5WebView.getUrl().contains("study.jsp")
                        ||x5WebView.getUrl().contains("schedule.jsp")
                        ||x5WebView.getUrl().contains("online.jsp")
                        ||x5WebView.getUrl().contains("login.html")
                        ||x5WebView.getUrl().contains("personalCenter.jsp")){
                    this.tbsSuiteExit();

                }else {
                    x5WebView.goBack();
                    return true;
                }

            } else {
                this.tbsSuiteExit();

            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }



    /**
     * 错误页面逻辑处理
     */
    private void errorOperation() {
        // 隐藏webview
        x5WebView.setVisibility(View.INVISIBLE);

        if (View.INVISIBLE == mTopProgress.getVisibility()) {
            mTopProgress.setVisibility(View.VISIBLE);
            // TVLoading.setVisibility(View.VISIBLE);
        }
        // 3.5s 加载 0->80 进度的加载 为了实现,特意调节长了事件
        mTopProgress.setCurProgress(80, 3500,
                new HProgressBarLoading.OnEndListener() {
                    @Override
                    public void onEnd() {
                        // 3.5s 加载 80->100 进度的加载
                        mTopProgress.setCurProgress(100, 3500,
                                new HProgressBarLoading.OnEndListener() {
                                    @Override
                                    public void onEnd() {
                                        finishOperation(false);
                                    }
                                });
                    }
                });
    }


    /**
     * 结束进行的操作
     */
    private void finishOperation(boolean flag) {
        // 最后加载设置100进度
        mTopProgress.setNormalProgress(100);
        // 显示网络异常布局
        mTvCenterBadnet.setVisibility(flag ? View.INVISIBLE : View.VISIBLE);
        // 点击重新连接网络
        mTvCenterBadnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvCenterBadnet.setVisibility(View.INVISIBLE);
                // 重新加载网页
                x5WebView.reload();
            }
        });
        hideProgressWithAnim();
    }

    /**
     * 隐藏加载对话框
     */
    private void hideProgressWithAnim() {
        AnimationSet animation = getDismissAnim(this);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTopProgress.setVisibility(View.INVISIBLE);
                // TVLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mTopProgress.startAnimation(animation);
    }

    /**
     * 获取消失的动画
     *
     * @param context
     * @return
     */
    private AnimationSet getDismissAnim(Context context) {
        AnimationSet dismiss = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(1000);
        dismiss.addAnimation(alpha);
        return dismiss;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    if (null != mUploadMessage) {
                        Uri result = data == null || resultCode != RESULT_OK ? null
                                : data.getData();
                        mUploadMessage.onReceiveValue(result);
                        mUploadMessage = null;
                    }
                    if (null != mUploadCallbackAboveL) {
                        Uri result = data == null || resultCode != RESULT_OK ? null
                                : data.getData();
                        mUploadCallbackAboveL.onReceiveValue(new Uri[]{result});
                        mUploadCallbackAboveL = null;
                    }
                    break;
                default:
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (null != mUploadMessage) {
                mUploadMessage.onReceiveValue(null);
                mUploadMessage = null;
            }

        }

       /* if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data
                    .getData();
            if (mUploadCallbackAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (mUploadMessage != null) {
                if (result != null) {
                    String path = getPath(getApplicationContext(), result);
                    Uri uri = Uri.fromFile(new File(path));
                    mUploadMessage.onReceiveValue(uri);
                } else {
                    mUploadMessage.onReceiveValue(imageUri);
                }
                mUploadMessage = null;

            }
        }*/
    }


    @SuppressWarnings("null")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode,
                                        Intent data) {
        if (requestCode != FILECHOOSER_RESULTCODE
                || mUploadCallbackAboveL == null) {
            return;
        }

        Uri[] results = null;

        if (resultCode == Activity.RESULT_OK) {

            if (data == null) {

                results = new Uri[]{imageUri};
            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();

                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        if (results != null) {
            mUploadCallbackAboveL.onReceiveValue(results);
            mUploadCallbackAboveL = null;
        } else {
            results = new Uri[]{imageUri};
            mUploadCallbackAboveL.onReceiveValue(results);
            mUploadCallbackAboveL = null;
        }

        return;
    }



    private void tbsSuiteExit() {
        // exit TbsSuite?
        dialog = new Dialog(MainActivityX5.this,
                android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth);
        View view = getLayoutInflater().inflate(R.layout.dialog_judge, null);
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText("是否退出程序?");
        view.findViewById(R.id.yes).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });
        view.findViewById(R.id.no).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }

                });
        dialog.setContentView(view);
        dialog.show();
        // } else {
        // }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/"
                            + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection,
                        selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri,
                                       String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri
                .getAuthority());
    }




    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (x5WebView != null) {
            x5WebView.destroy();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (x5WebView != null) {
            x5WebView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (x5WebView != null) {
            x5WebView.onResume();
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
    }




    protected void take() {
        // TODO Auto-generated method stub
        File imageStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyApp");
        // Create the storage directory if it does not exist
        if (!imageStorageDir.exists()) {
            imageStorageDir.mkdirs();
        }
        File file = new File(imageStorageDir + File.separator + "IMG_"
                + String.valueOf(System.currentTimeMillis()) + ".jpg");
        imageUri = Uri.fromFile(file);

        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(
                captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent i = new Intent(captureIntent);
            i.setComponent(new ComponentName(res.activityInfo.packageName,
                    res.activityInfo.name));
            i.setPackage(packageName);
            i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraIntents.add(i);

        }
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        Intent chooserIntent = Intent.createChooser(i, "Image Chooser");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                cameraIntents.toArray(new Parcelable[] {}));
        MainActivityX5.this.startActivityForResult(chooserIntent,
                FILECHOOSER_RESULTCODE);

    }
}
