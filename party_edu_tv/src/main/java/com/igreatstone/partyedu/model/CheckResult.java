package com.igreatstone.partyedu.model;

/**
 * Created by yy on 2017/12/15.
 */

public class CheckResult {

    /**
     * msg : 查询APK更新策略成功
     * datas : {"downloadUrl":"https://www.baidu3.com/","isEnforceUpdate":"0","businessName":"党教业务"}
     * status : 1
     */

    private String msg;
    private String datas;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
