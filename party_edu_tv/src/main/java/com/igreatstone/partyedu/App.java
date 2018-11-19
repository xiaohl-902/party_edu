package com.igreatstone.partyedu;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.igreatstone.partyedu.model.ApiUrl;
import com.igreatstone.partyedu.model.Terminal;

/**
 * Created by yy on 2017/11/7.
 */

public class App extends Application {

    public static App mInstance;

    private ApiUrl apiUrl;
    private Terminal terminal;

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

    public ApiUrl getApiUrl() {
        return apiUrl == null ? new ApiUrl() : apiUrl;
    }

    public void setApiUrl(ApiUrl apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Terminal getTerminal() {
        return terminal == null ? new Terminal() : terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
}
