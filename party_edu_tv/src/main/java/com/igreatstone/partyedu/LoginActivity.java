package com.igreatstone.partyedu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.igreatstone.partyedu.common.CommonUtil;
import com.igreatstone.partyedu.common.PreferenceUtil;
import com.igreatstone.partyedu.common.ToastUtil;
import com.igreatstone.partyedu.model.Config;
import com.igreatstone.partyedu.model.LoginData;
import com.igreatstone.partyedu.model.ApiUrl;

/**
 * Created by yy on 2017/11/7.
 */

public class LoginActivity extends Activity {
    SpinKitView spinKitView;
    Sprite drawable = null;
    RelativeLayout relativelayout;
    TextView textview;
    private boolean isReg = false;
    private int loginCount=0;//老版党教割接-自动绑定mac后重新登陆次数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getConfig();
    }

    /**
     * 调用接口获取配置信息
     */
    private void getConfig() {
       String apiConfigAddr = BuildConfig.API_CONFIG_URL;//编译时动态替换
        OkGo.<String>get(apiConfigAddr).tag("this").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String configData = response.body();
                if (!TextUtils.isEmpty(configData)) {
                    Config config = new Gson().fromJson(configData, Config.class);
                    ApiUrl apiUrl = new ApiUrl();
                    apiUrl.setApiConfigAddr(config.getCONFIG_URL());
                    apiUrl.setApiLoginAddr(config.getLOGIN_URL());
                    apiUrl.setLoginSkipUrl(config.getLOGIN_SKIP_URL());
                    Config.GeneralIpsBean ipsBean = config.getGeneral_ips().get(0);
                    apiUrl.setCheckVersion(ipsBean.getCheckVersion_new());
                    apiUrl.setModULoginByNamepwd(ipsBean.getModULoginByNamepwd());//自动绑定mac action
                    apiUrl.setConfig(config);
                    App.getInstance().setApiUrl(apiUrl);

                    String loginAddr = BuildConfig.API_LOGIN_URL;
                    String loginUrl = loginAddr + "&macAddress=" +  CommonUtil.getMac() + "&businessCode=" + config.getBusinessCode() + "&platformCode=" + config.getPlatformCode() + "&versionCode=" + config.getAPI_VERSION() + "&businessId=" + config.getBusinessId();
                    goLogin(loginUrl);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

                goSetting();

            }
        });

    }

    /**
     * 登录
     * @param loginUrl 登录地址
     */
    private void goLogin(final String loginUrl) {
        OkGo.<String>get(loginUrl).tag("this").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String loginData = response.body();
                if (!TextUtils.isEmpty(loginData)) {
                    LoginData login = new Gson().fromJson(loginData, LoginData.class);
                    int status = login.getStatus();
                    if (status == 1) {//mac地址已绑定
                        isReg = true;
                        String webUrl = login.getDatas_androidWebURList().get(0).getUrl();
                        if (webUrl == null) {
                            ToastUtil.showLong(LoginActivity.this, "该用户还没关联党教的URL");
                            return;
                        }
                        if (login.getDatas_androidWebURList().get(0).getAgentServerUrl() == null) {
                            ToastUtil.showLong(LoginActivity.this, "该用户还没关联党教网页浏览端口");
                        }
                        String agentServerUrl = login.getDatas_androidWebURList().get(0).getAgentServerUrl();
                        String[] strs = agentServerUrl.split(":");
                        PreferenceUtil.put("agentIp", strs.length > 0 ? strs[0] : "");
                        PreferenceUtil.put("agentPort", strs.length > 1 ? strs[1] : "");
                        PreferenceUtil.put("loginToken", login.getDatas().get(0).getLoginToken());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("isReg", isReg);
                        bundle.putString("loginUrl", loginData);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else if (status == 0) {//mac地址未绑定
                        //老版党教割接-未注册时读取本地用户信息自动绑定mac地址并登陆
                        String[] arr = getUser();
                        if (arr.length == 2 && loginCount <= 3) {//只重试3次，超过了就不绑定 防止死循环
                            String username = arr[0], passwd = arr[1];
                            if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(passwd)) {
                                username = username.toUpperCase();
                                ToastUtil.showLong(LoginActivity.this,username+","+passwd);
                                ApiUrl apiUrl = App.getInstance().getApiUrl();
                                Config config  = apiUrl.getConfig();
                                String bindUrl = apiUrl.getModULoginByNamepwd() + "&name=" + username + "&password=" + passwd + "&businessId=" + config.getBusinessId() + "&type=macAddress&typeValue=" + CommonUtil.getMac();
                                bindMac(bindUrl, loginUrl);
                                return;
                            }
                        }

                        isReg = false;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("isReg", isReg);
                        bundle.putString("loadingUrl", BuildConfig.FIRST_LOGIN_URL);//ApiUrl.LOGINURL
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                }
            }
            @Override
            public void onError(Response<String> response) {
                super.onError(response);

                goSetting();

            }
        });
    }

    //老版党教割接-自动绑定mac
    //bindIp+"&name="+userName+"&password="+psw+"&businessId="+businessId+"&type=macAddress&typeValue="+macAddress,
    private void bindMac(String bindUrl, final String loginUrl){
        OkGo.<String>get(bindUrl).tag("this").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                goLogin(loginUrl);//自动绑定mac成功后再次尝试登陆
                loginCount++;
            }
            @Override
            public void onError(Response<String> response) {
                super.onError(response);

                goSetting();

            }
        });
    }

    //老版党教割接-读取本地用户信息
    private String[] getUser(){
        String[] arr=new String[2];
        try {
            SQLiteDatabase db = this.openOrCreateDatabase("c.db", Context.MODE_PRIVATE, null);
            Cursor c = db.rawQuery("SELECT * FROM user WHERE id=1",new String[]{});
            if(c.moveToNext()){
                arr[0]=c.getString(c.getColumnIndex("username"));
                arr[1]=c.getString(c.getColumnIndex("passwd"));
            }
            c.close();
            db.close();
        }catch (Exception e){
            e.printStackTrace();
            //CrashReport.postCatchedException(e);  // bugly会将这个throwable上报
        }
        return arr;
    }

    private void goSetting(){
        AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this)
                .setIcon(R.mipmap.ic_launcher)//设置标题的图片
                .setTitle("系统提示")//设置对话框的标题
                .setMessage("网络异常，确定进入设置界面？")//设置对话框的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //网络异常 跳转到设置界面
                        Intent	mintent = new Intent();
                        mintent.setComponent(new ComponentName("com.android.settings",
                                "com.android.settings.Settings"));
                        startActivity(mintent);
                        dialog.dismiss();
                    }
                })
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

        dialog.show();
    }

    private void initView() {
        RelativeLayout.LayoutParams framelayout_params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        relativelayout = new RelativeLayout(this);
//        relativelayout.setBackgroundColor(Color.parseColor("#6A5ACD"));
        relativelayout.setLayoutParams(framelayout_params);
        textview = new TextView(this);
        textview.setText("正在");
        textview.setTextColor(Color.parseColor("#FFFFFF"));
        textview.setTextSize(100);
        textview.setBackgroundColor(Color.BLUE);
        relativelayout.addView(textview);
        relativelayout.setVisibility(View.VISIBLE);

        spinKitView = new SpinKitView(this);
        drawable = new Circle();
        drawable.setBounds(0, 0, 200, 200);
        drawable.setColor(Color.WHITE);
        spinKitView.setIndeterminateDrawable(drawable);
    }
}
