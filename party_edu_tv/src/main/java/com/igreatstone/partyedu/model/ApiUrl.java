package com.igreatstone.partyedu.model;

import android.content.Context;

import com.igreatstone.partyedu.R;

/**
 * Created by yy on 2017/11/20.
 */

public class ApiUrl {

    private String loginSkipUrl = "";// 根据接口获取的对应地区登录跳转地址
    private String apiConfigAddr = "";// 获取配置接口地址
    private String apiLoginAddr = "";// 接口登录地址
    private String checkVersion = "";// 线上版本号
    private String modULoginByNamepwd="";//老版党教割接-根据用户名和密码绑定自动绑定mac地址action
    private Config config;

    public String getLoginSkipUrl() {
        return loginSkipUrl;
    }

    public void setLoginSkipUrl(String loginSkipUrl) {
        this.loginSkipUrl = loginSkipUrl;
    }

    public String getApiConfigAddr() {
        return apiConfigAddr;
    }

    public void setApiConfigAddr(String apiConfigAddr) {
        this.apiConfigAddr = apiConfigAddr;
    }

    public String getApiLoginAddr() {
        return apiLoginAddr;
    }

    public void setApiLoginAddr(String apiLoginAddr) {
        this.apiLoginAddr = apiLoginAddr;
    }

    public String getCheckVersion() {
        return checkVersion;
    }

    public void setCheckVersion(String checkVersion) {
        this.checkVersion = checkVersion;
    }

    public String getModULoginByNamepwd() {
        return modULoginByNamepwd;
    }

    public void setModULoginByNamepwd(String modULoginByNamepwd) {
        this.modULoginByNamepwd = modULoginByNamepwd;
    }

    public Config getConfig() {
        return config == null ? new Config() : config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
