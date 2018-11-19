package com.igreatstone.partyconst;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by yy on 2017/11/7.
 */

public class App extends Application {

    public static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //CrashReport.initCrashReport(getApplicationContext());
        Utils.init(this);
    }

    public static App getInstance() {
        return mInstance;
    }

}
