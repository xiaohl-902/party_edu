package com.igreatstone.partyedu.model;

/**
 * Created by yy on 2017/11/30.
 */

public class VideoPlayBean {


    /**
     * courseId :
     * menuId : 0506120456940003
     * videoId : 11291528557630002
     * videoUrl : http://10.0.1.122:8080/HS_CMS_COTENT_MANAGEMENT_SYS/cvideo2/partytech/1.ts
     * mid : 05261605426090000
     * menuName : 党风廉政建设
     * vType : 1
     * loginToken : 11126d834032346d1bc97381383bf1dc0d4210f74c16b58dac9d71cd41910810014de7b10c12c0142577824ee5af76e6ddd6057df52f07e08b91f5410644b398bb4051fa4f6cd975420bc31500795906d1afc9d6044377791d9acfd11bcb984492f1990f7ec5cc19df07ce452cdf90b5c5ef6dfff56e6de4bcc08fa966e14b241d104b6eeda2551f4d9d3efa4a7d2a0b62fc19a0517ef2ca512fdedef1650dac3f14ec6fe2ee527858e285fdeb09a52235896c1d12d4b56298eefd53caff0ca0aaba767f2bb4399a
     * lastPlayTimes : 0
     * isComplete :
     * logPlayId :
     * ipAddress : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=playLog
     * ifStart : 0
     * onlineLogIp : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=onlineLog
     * onlineLoginId : 12011132535284962
     * onlineTime : 30000
     * playTime : 1000
     */

    private String courseId;
    private String menuId;
    private String videoId;
    private String videoUrl;
    private String mid;
    private String menuName;
    private int vType;
    private String loginToken;
    private String lastPlayTimes;
    private String isComplete;
    private String logPlayId;
    private String ipAddress;
    private int ifStart;
    private String onlineLogIp;
    private String onlineLoginId;
    private String onlineTime;
    private String playTime;
    private String vName;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getVType() {
        return vType;
    }

    public void setVType(int vType) {
        this.vType = vType;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getLastPlayTimes() {
        return lastPlayTimes;
    }

    public void setLastPlayTimes(String lastPlayTimes) {
        this.lastPlayTimes = lastPlayTimes;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public String getLogPlayId() {
        return logPlayId;
    }

    public void setLogPlayId(String logPlayId) {
        this.logPlayId = logPlayId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getIfStart() {
        return ifStart;
    }

    public void setIfStart(int ifStart) {
        this.ifStart = ifStart;
    }

    public String getOnlineLogIp() {
        return onlineLogIp;
    }

    public void setOnlineLogIp(String onlineLogIp) {
        this.onlineLogIp = onlineLogIp;
    }

    public String getOnlineLoginId() {
        return onlineLoginId;
    }

    public void setOnlineLoginId(String onlineLoginId) {
        this.onlineLoginId = onlineLoginId;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }
}
