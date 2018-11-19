package com.igreatstone.partyedu.model;

import java.util.List;

/**
 * Created by yy on 2017/11/7.
 */

public class LoadUrlBean {

    /**
     * msg : 以token得到url地址成功
     * datas_androidWebURList : []
     * status : 1
     */

    private String msg;
    private int status;
    private List<?> datas_androidWebURList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<?> getDatas_androidWebURList() {
        return datas_androidWebURList;
    }

    public void setDatas_androidWebURList(List<?> datas_androidWebURList) {
        this.datas_androidWebURList = datas_androidWebURList;
    }
}
