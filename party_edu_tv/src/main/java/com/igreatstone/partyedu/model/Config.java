package com.igreatstone.partyedu.model;

import java.util.List;

/**
 * Created by yy on 2017/11/20.
 */

public class Config {


    /**
     * general_ips : [{"getAllarea":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getAllarea","getAreaByPid":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getAreaByPid","getConfig":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getConfig","getBusiness":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getBusiness","getBusinessAllMenu":"http://10.0.1.122:8080/CMSInterfaceManage//myServletControls.do?action=getBusinessAllMenu","getMenus":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getMenus","adminGetMenu":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=adminGetMenu","setMenuMid":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=setMenuMid","cleanMenuMid":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=cleanMenuMid","getMenuByPid":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getMenuByPid","macLogin":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=macLogin","nameLogin":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=nameLogin","adminLogin":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=adminLogin","onlineLog":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=onlineLog","playLog":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=playLog","getStudyMenuTreeBy_NoLogin":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getStudyMenuTreeBy_NoLogin","getMenus1_new":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getMenus1_new","getMenuByPid_new":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getMenuByPid_new","checkVersion":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=checkVersion","getAdminManageAreaList":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getAdminManageAreaList","getUGroupUTreeGridByBusses":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getUGroupUTreeGridByBusses","adminGetMenuByBusinessId":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=adminGetMenuByBusinessId","getAdminRolesByBid":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getAdminRolesByBid","getUserUGByBid":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getUserUGByBid","macLogin_dj":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=macLogin_dj","modULoginByNamepwd":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=modULoginByNamepwd","getUserRealInfoByUid":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getUserRealInfoByUid","getReBindingEwm":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getReBindingEwm","saveKeyValue":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=saveKeyValue","getKeyValue":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getKeyValue","nameFindePwd":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=nameFindePwd","checkVersion_new":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=checkVersion_new"}]
     * heartbeatTime : 30000
     * heartbeatTime_play : 30000
     * API_VERSION : V1.0
     * IMAGE_RESOURCES_ADDRESS : http://10.0.1.122:8080/HS_CMS_COTENT_MANAGEMENT_SYS/cimages2/partytech/
     * right_imgPath : http://10.0.1.122:8080/rightsManageSys/cimage/
     * reBindingEwmPrePath : http://10.0.1.122:8080/CMSInterfaceManage/erwimage/partytech/
     * platformCode : HSKJ012
     * businessCode : partytech
     * businessId : 05051357441470000
     * IP : http://10.0.1.122:8080/
     * CONFIG_URL : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getMenusByFileJson&fname=configs/partytech/partytechConfigs_android.json
     * LOGIN_URL : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=macLogin_dj
     * LOGIN_SKIP_URL : http://124.232.153.82:8015/cadmin_login/jsp/login.jsp
     * DJ_urls : [{"DJ_CONTENT_getNotice":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getNotice","DJ_CONTENT_getRolling":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getRolling","DJ_CONTENT_getPartytechVideoListV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechVideoListV10","DJ_CONTENT_getPartytechVideoDetailV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechVideoDetailV10","DJ_CONTENT_getPartytechImagetextListV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechImagetextListV10","DJ_CONTENT_getPartytechImagetextDetailV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechImagetextDetailV10","DJ_CONTENT_getPartytechContentListV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechContentListV10","DJ_CONTENT_getPartytechContentDetailV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechContentDetailV10","DJ_CONTENT_getPartytechStudyContentList_V1_0":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechStudyContentList_V1_0","DJ_CONTENT_getPartytechVideowebListV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechVideowebListV10","DJ_CONTENT_getPartytechVideoliveListV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechVideoliveListV10","DJ_CONTENT_getCommonDictionaryListV10":"http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getCommonDictionaryListV10","DJ_CONTENT_getPartytechTopicListV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechTopicListV10","DJ_CONTENT_partytechTopicSaveAnswerV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=partytechTopicSaveAnswerV10","DJ_CONTENT_partytechTopicSubmitExamV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=partytechTopicSubmitExamV10","DJ_CONTENT_partytechTopicGiveUpExamV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=partytechTopicGiveUpExamV10","DJ_CONTENT_partytechCourceListV10":"http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=partytechCourseListV10","DJ_bussnessId":"05051357441470000","getMenusByFileJson":"http://10.0.1.122:8080/CMSInterfaceManage//myServletControls.do?action=getMenusByFileJson","DJ_webMenuFjson":"DJ_webMenuFjson.json","DJ_AndroidMenuFjson":"DJ_AdnroidMenuFjson.json","getPartytechImagetextListV10":"http://10.0.1.122:8080/CMSInterfaceManage//Partytech_ServletControls.do?action=getPartytechImagetextListV10"}]
     */

    private String heartbeatTime;
    private String heartbeatTime_play;
    private String API_VERSION;
    private String IMAGE_RESOURCES_ADDRESS;
    private String right_imgPath;
    private String reBindingEwmPrePath;
    private String platformCode;
    private String businessCode;
    private String businessId;
    private String IP;
    private String CONFIG_URL;
    private String LOGIN_URL;
    private String LOGIN_SKIP_URL;
    private List<GeneralIpsBean> general_ips;
    private List<DJUrlsBean> DJ_urls;

    public String getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(String heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }

    public String getHeartbeatTime_play() {
        return heartbeatTime_play;
    }

    public void setHeartbeatTime_play(String heartbeatTime_play) {
        this.heartbeatTime_play = heartbeatTime_play;
    }

    public String getAPI_VERSION() {
        return API_VERSION;
    }

    public void setAPI_VERSION(String API_VERSION) {
        this.API_VERSION = API_VERSION;
    }

    public String getIMAGE_RESOURCES_ADDRESS() {
        return IMAGE_RESOURCES_ADDRESS;
    }

    public void setIMAGE_RESOURCES_ADDRESS(String IMAGE_RESOURCES_ADDRESS) {
        this.IMAGE_RESOURCES_ADDRESS = IMAGE_RESOURCES_ADDRESS;
    }

    public String getRight_imgPath() {
        return right_imgPath;
    }

    public void setRight_imgPath(String right_imgPath) {
        this.right_imgPath = right_imgPath;
    }

    public String getReBindingEwmPrePath() {
        return reBindingEwmPrePath;
    }

    public void setReBindingEwmPrePath(String reBindingEwmPrePath) {
        this.reBindingEwmPrePath = reBindingEwmPrePath;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getCONFIG_URL() {
        return CONFIG_URL;
    }

    public void setCONFIG_URL(String CONFIG_URL) {
        this.CONFIG_URL = CONFIG_URL;
    }

    public String getLOGIN_URL() {
        return LOGIN_URL;
    }

    public void setLOGIN_URL(String LOGIN_URL) {
        this.LOGIN_URL = LOGIN_URL;
    }

    public String getLOGIN_SKIP_URL() {
        return LOGIN_SKIP_URL;
    }

    public void setLOGIN_SKIP_URL(String LOGIN_SKIP_URL) {
        this.LOGIN_SKIP_URL = LOGIN_SKIP_URL;
    }

    public List<GeneralIpsBean> getGeneral_ips() {
        return general_ips;
    }

    public void setGeneral_ips(List<GeneralIpsBean> general_ips) {
        this.general_ips = general_ips;
    }

    public List<DJUrlsBean> getDJ_urls() {
        return DJ_urls;
    }

    public void setDJ_urls(List<DJUrlsBean> DJ_urls) {
        this.DJ_urls = DJ_urls;
    }

    public static class GeneralIpsBean {
        /**
         * getAllarea : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getAllarea
         * getAreaByPid : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getAreaByPid
         * getConfig : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getConfig
         * getBusiness : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getBusiness
         * getBusinessAllMenu : http://10.0.1.122:8080/CMSInterfaceManage//myServletControls.do?action=getBusinessAllMenu
         * getMenus : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getMenus
         * adminGetMenu : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=adminGetMenu
         * setMenuMid : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=setMenuMid
         * cleanMenuMid : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=cleanMenuMid
         * getMenuByPid : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getMenuByPid
         * macLogin : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=macLogin
         * nameLogin : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=nameLogin
         * adminLogin : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=adminLogin
         * onlineLog : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=onlineLog
         * playLog : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=playLog
         * getStudyMenuTreeBy_NoLogin : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getStudyMenuTreeBy_NoLogin
         * getMenus1_new : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getMenus1_new
         * getMenuByPid_new : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getMenuByPid_new
         * checkVersion : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=checkVersion
         * getAdminManageAreaList : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getAdminManageAreaList
         * getUGroupUTreeGridByBusses : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getUGroupUTreeGridByBusses
         * adminGetMenuByBusinessId : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=adminGetMenuByBusinessId
         * getAdminRolesByBid : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getAdminRolesByBid
         * getUserUGByBid : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getUserUGByBid
         * macLogin_dj : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=macLogin_dj
         * modULoginByNamepwd : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=modULoginByNamepwd
         * getUserRealInfoByUid : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getUserRealInfoByUid
         * getReBindingEwm : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getReBindingEwm
         * saveKeyValue : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=saveKeyValue
         * getKeyValue : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getKeyValue
         * nameFindePwd : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=nameFindePwd
         * checkVersion_new : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=checkVersion_new
         */

        private String getAllarea;
        private String getAreaByPid;
        private String getConfig;
        private String getBusiness;
        private String getBusinessAllMenu;
        private String getMenus;
        private String adminGetMenu;
        private String setMenuMid;
        private String cleanMenuMid;
        private String getMenuByPid;
        private String macLogin;
        private String nameLogin;
        private String adminLogin;
        private String onlineLog;
        private String playLog;
        private String getStudyMenuTreeBy_NoLogin;
        private String getMenus1_new;
        private String getMenuByPid_new;
        private String checkVersion;
        private String getAdminManageAreaList;
        private String getUGroupUTreeGridByBusses;
        private String adminGetMenuByBusinessId;
        private String getAdminRolesByBid;
        private String getUserUGByBid;
        private String macLogin_dj;
        private String modULoginByNamepwd;
        private String getUserRealInfoByUid;
        private String getReBindingEwm;
        private String saveKeyValue;
        private String getKeyValue;
        private String nameFindePwd;
        private String checkVersion_new;

        public String getGetAllarea() {
            return getAllarea;
        }

        public void setGetAllarea(String getAllarea) {
            this.getAllarea = getAllarea;
        }

        public String getGetAreaByPid() {
            return getAreaByPid;
        }

        public void setGetAreaByPid(String getAreaByPid) {
            this.getAreaByPid = getAreaByPid;
        }

        public String getGetConfig() {
            return getConfig;
        }

        public void setGetConfig(String getConfig) {
            this.getConfig = getConfig;
        }

        public String getGetBusiness() {
            return getBusiness;
        }

        public void setGetBusiness(String getBusiness) {
            this.getBusiness = getBusiness;
        }

        public String getGetBusinessAllMenu() {
            return getBusinessAllMenu;
        }

        public void setGetBusinessAllMenu(String getBusinessAllMenu) {
            this.getBusinessAllMenu = getBusinessAllMenu;
        }

        public String getGetMenus() {
            return getMenus;
        }

        public void setGetMenus(String getMenus) {
            this.getMenus = getMenus;
        }

        public String getAdminGetMenu() {
            return adminGetMenu;
        }

        public void setAdminGetMenu(String adminGetMenu) {
            this.adminGetMenu = adminGetMenu;
        }

        public String getSetMenuMid() {
            return setMenuMid;
        }

        public void setSetMenuMid(String setMenuMid) {
            this.setMenuMid = setMenuMid;
        }

        public String getCleanMenuMid() {
            return cleanMenuMid;
        }

        public void setCleanMenuMid(String cleanMenuMid) {
            this.cleanMenuMid = cleanMenuMid;
        }

        public String getGetMenuByPid() {
            return getMenuByPid;
        }

        public void setGetMenuByPid(String getMenuByPid) {
            this.getMenuByPid = getMenuByPid;
        }

        public String getMacLogin() {
            return macLogin;
        }

        public void setMacLogin(String macLogin) {
            this.macLogin = macLogin;
        }

        public String getNameLogin() {
            return nameLogin;
        }

        public void setNameLogin(String nameLogin) {
            this.nameLogin = nameLogin;
        }

        public String getAdminLogin() {
            return adminLogin;
        }

        public void setAdminLogin(String adminLogin) {
            this.adminLogin = adminLogin;
        }

        public String getOnlineLog() {
            return onlineLog;
        }

        public void setOnlineLog(String onlineLog) {
            this.onlineLog = onlineLog;
        }

        public String getPlayLog() {
            return playLog;
        }

        public void setPlayLog(String playLog) {
            this.playLog = playLog;
        }

        public String getGetStudyMenuTreeBy_NoLogin() {
            return getStudyMenuTreeBy_NoLogin;
        }

        public void setGetStudyMenuTreeBy_NoLogin(String getStudyMenuTreeBy_NoLogin) {
            this.getStudyMenuTreeBy_NoLogin = getStudyMenuTreeBy_NoLogin;
        }

        public String getGetMenus1_new() {
            return getMenus1_new;
        }

        public void setGetMenus1_new(String getMenus1_new) {
            this.getMenus1_new = getMenus1_new;
        }

        public String getGetMenuByPid_new() {
            return getMenuByPid_new;
        }

        public void setGetMenuByPid_new(String getMenuByPid_new) {
            this.getMenuByPid_new = getMenuByPid_new;
        }

        public String getCheckVersion() {
            return checkVersion;
        }

        public void setCheckVersion(String checkVersion) {
            this.checkVersion = checkVersion;
        }

        public String getGetAdminManageAreaList() {
            return getAdminManageAreaList;
        }

        public void setGetAdminManageAreaList(String getAdminManageAreaList) {
            this.getAdminManageAreaList = getAdminManageAreaList;
        }

        public String getGetUGroupUTreeGridByBusses() {
            return getUGroupUTreeGridByBusses;
        }

        public void setGetUGroupUTreeGridByBusses(String getUGroupUTreeGridByBusses) {
            this.getUGroupUTreeGridByBusses = getUGroupUTreeGridByBusses;
        }

        public String getAdminGetMenuByBusinessId() {
            return adminGetMenuByBusinessId;
        }

        public void setAdminGetMenuByBusinessId(String adminGetMenuByBusinessId) {
            this.adminGetMenuByBusinessId = adminGetMenuByBusinessId;
        }

        public String getGetAdminRolesByBid() {
            return getAdminRolesByBid;
        }

        public void setGetAdminRolesByBid(String getAdminRolesByBid) {
            this.getAdminRolesByBid = getAdminRolesByBid;
        }

        public String getGetUserUGByBid() {
            return getUserUGByBid;
        }

        public void setGetUserUGByBid(String getUserUGByBid) {
            this.getUserUGByBid = getUserUGByBid;
        }

        public String getMacLogin_dj() {
            return macLogin_dj;
        }

        public void setMacLogin_dj(String macLogin_dj) {
            this.macLogin_dj = macLogin_dj;
        }

        public String getModULoginByNamepwd() {
            return modULoginByNamepwd;
        }

        public void setModULoginByNamepwd(String modULoginByNamepwd) {
            this.modULoginByNamepwd = modULoginByNamepwd;
        }

        public String getGetUserRealInfoByUid() {
            return getUserRealInfoByUid;
        }

        public void setGetUserRealInfoByUid(String getUserRealInfoByUid) {
            this.getUserRealInfoByUid = getUserRealInfoByUid;
        }

        public String getGetReBindingEwm() {
            return getReBindingEwm;
        }

        public void setGetReBindingEwm(String getReBindingEwm) {
            this.getReBindingEwm = getReBindingEwm;
        }

        public String getSaveKeyValue() {
            return saveKeyValue;
        }

        public void setSaveKeyValue(String saveKeyValue) {
            this.saveKeyValue = saveKeyValue;
        }

        public String getGetKeyValue() {
            return getKeyValue;
        }

        public void setGetKeyValue(String getKeyValue) {
            this.getKeyValue = getKeyValue;
        }

        public String getNameFindePwd() {
            return nameFindePwd;
        }

        public void setNameFindePwd(String nameFindePwd) {
            this.nameFindePwd = nameFindePwd;
        }

        public String getCheckVersion_new() {
            return checkVersion_new;
        }

        public void setCheckVersion_new(String checkVersion_new) {
            this.checkVersion_new = checkVersion_new;
        }
    }

    public static class DJUrlsBean {
        /**
         * DJ_CONTENT_getNotice : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getNotice
         * DJ_CONTENT_getRolling : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getRolling
         * DJ_CONTENT_getPartytechVideoListV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechVideoListV10
         * DJ_CONTENT_getPartytechVideoDetailV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechVideoDetailV10
         * DJ_CONTENT_getPartytechImagetextListV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechImagetextListV10
         * DJ_CONTENT_getPartytechImagetextDetailV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechImagetextDetailV10
         * DJ_CONTENT_getPartytechContentListV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechContentListV10
         * DJ_CONTENT_getPartytechContentDetailV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechContentDetailV10
         * DJ_CONTENT_getPartytechStudyContentList_V1_0 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechStudyContentList_V1_0
         * DJ_CONTENT_getPartytechVideowebListV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechVideowebListV10
         * DJ_CONTENT_getPartytechVideoliveListV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechVideoliveListV10
         * DJ_CONTENT_getCommonDictionaryListV10 : http://10.0.1.122:8080/CMSInterfaceManage/myServletControls.do?action=getCommonDictionaryListV10
         * DJ_CONTENT_getPartytechTopicListV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=getPartytechTopicListV10
         * DJ_CONTENT_partytechTopicSaveAnswerV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=partytechTopicSaveAnswerV10
         * DJ_CONTENT_partytechTopicSubmitExamV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=partytechTopicSubmitExamV10
         * DJ_CONTENT_partytechTopicGiveUpExamV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=partytechTopicGiveUpExamV10
         * DJ_CONTENT_partytechCourceListV10 : http://10.0.1.122:8080/CMSInterfaceManage/Partytech_ServletControls.do?action=partytechCourseListV10
         * DJ_bussnessId : 05051357441470000
         * getMenusByFileJson : http://10.0.1.122:8080/CMSInterfaceManage//myServletControls.do?action=getMenusByFileJson
         * DJ_webMenuFjson : DJ_webMenuFjson.json
         * DJ_AndroidMenuFjson : DJ_AdnroidMenuFjson.json
         * getPartytechImagetextListV10 : http://10.0.1.122:8080/CMSInterfaceManage//Partytech_ServletControls.do?action=getPartytechImagetextListV10
         */

        private String DJ_CONTENT_getNotice;
        private String DJ_CONTENT_getRolling;
        private String DJ_CONTENT_getPartytechVideoListV10;
        private String DJ_CONTENT_getPartytechVideoDetailV10;
        private String DJ_CONTENT_getPartytechImagetextListV10;
        private String DJ_CONTENT_getPartytechImagetextDetailV10;
        private String DJ_CONTENT_getPartytechContentListV10;
        private String DJ_CONTENT_getPartytechContentDetailV10;
        private String DJ_CONTENT_getPartytechStudyContentList_V1_0;
        private String DJ_CONTENT_getPartytechVideowebListV10;
        private String DJ_CONTENT_getPartytechVideoliveListV10;
        private String DJ_CONTENT_getCommonDictionaryListV10;
        private String DJ_CONTENT_getPartytechTopicListV10;
        private String DJ_CONTENT_partytechTopicSaveAnswerV10;
        private String DJ_CONTENT_partytechTopicSubmitExamV10;
        private String DJ_CONTENT_partytechTopicGiveUpExamV10;
        private String DJ_CONTENT_partytechCourceListV10;
        private String DJ_bussnessId;
        private String getMenusByFileJson;
        private String DJ_webMenuFjson;
        private String DJ_AndroidMenuFjson;
        private String getPartytechImagetextListV10;

        public String getDJ_CONTENT_getNotice() {
            return DJ_CONTENT_getNotice;
        }

        public void setDJ_CONTENT_getNotice(String DJ_CONTENT_getNotice) {
            this.DJ_CONTENT_getNotice = DJ_CONTENT_getNotice;
        }

        public String getDJ_CONTENT_getRolling() {
            return DJ_CONTENT_getRolling;
        }

        public void setDJ_CONTENT_getRolling(String DJ_CONTENT_getRolling) {
            this.DJ_CONTENT_getRolling = DJ_CONTENT_getRolling;
        }

        public String getDJ_CONTENT_getPartytechVideoListV10() {
            return DJ_CONTENT_getPartytechVideoListV10;
        }

        public void setDJ_CONTENT_getPartytechVideoListV10(String DJ_CONTENT_getPartytechVideoListV10) {
            this.DJ_CONTENT_getPartytechVideoListV10 = DJ_CONTENT_getPartytechVideoListV10;
        }

        public String getDJ_CONTENT_getPartytechVideoDetailV10() {
            return DJ_CONTENT_getPartytechVideoDetailV10;
        }

        public void setDJ_CONTENT_getPartytechVideoDetailV10(String DJ_CONTENT_getPartytechVideoDetailV10) {
            this.DJ_CONTENT_getPartytechVideoDetailV10 = DJ_CONTENT_getPartytechVideoDetailV10;
        }

        public String getDJ_CONTENT_getPartytechImagetextListV10() {
            return DJ_CONTENT_getPartytechImagetextListV10;
        }

        public void setDJ_CONTENT_getPartytechImagetextListV10(String DJ_CONTENT_getPartytechImagetextListV10) {
            this.DJ_CONTENT_getPartytechImagetextListV10 = DJ_CONTENT_getPartytechImagetextListV10;
        }

        public String getDJ_CONTENT_getPartytechImagetextDetailV10() {
            return DJ_CONTENT_getPartytechImagetextDetailV10;
        }

        public void setDJ_CONTENT_getPartytechImagetextDetailV10(String DJ_CONTENT_getPartytechImagetextDetailV10) {
            this.DJ_CONTENT_getPartytechImagetextDetailV10 = DJ_CONTENT_getPartytechImagetextDetailV10;
        }

        public String getDJ_CONTENT_getPartytechContentListV10() {
            return DJ_CONTENT_getPartytechContentListV10;
        }

        public void setDJ_CONTENT_getPartytechContentListV10(String DJ_CONTENT_getPartytechContentListV10) {
            this.DJ_CONTENT_getPartytechContentListV10 = DJ_CONTENT_getPartytechContentListV10;
        }

        public String getDJ_CONTENT_getPartytechContentDetailV10() {
            return DJ_CONTENT_getPartytechContentDetailV10;
        }

        public void setDJ_CONTENT_getPartytechContentDetailV10(String DJ_CONTENT_getPartytechContentDetailV10) {
            this.DJ_CONTENT_getPartytechContentDetailV10 = DJ_CONTENT_getPartytechContentDetailV10;
        }

        public String getDJ_CONTENT_getPartytechStudyContentList_V1_0() {
            return DJ_CONTENT_getPartytechStudyContentList_V1_0;
        }

        public void setDJ_CONTENT_getPartytechStudyContentList_V1_0(String DJ_CONTENT_getPartytechStudyContentList_V1_0) {
            this.DJ_CONTENT_getPartytechStudyContentList_V1_0 = DJ_CONTENT_getPartytechStudyContentList_V1_0;
        }

        public String getDJ_CONTENT_getPartytechVideowebListV10() {
            return DJ_CONTENT_getPartytechVideowebListV10;
        }

        public void setDJ_CONTENT_getPartytechVideowebListV10(String DJ_CONTENT_getPartytechVideowebListV10) {
            this.DJ_CONTENT_getPartytechVideowebListV10 = DJ_CONTENT_getPartytechVideowebListV10;
        }

        public String getDJ_CONTENT_getPartytechVideoliveListV10() {
            return DJ_CONTENT_getPartytechVideoliveListV10;
        }

        public void setDJ_CONTENT_getPartytechVideoliveListV10(String DJ_CONTENT_getPartytechVideoliveListV10) {
            this.DJ_CONTENT_getPartytechVideoliveListV10 = DJ_CONTENT_getPartytechVideoliveListV10;
        }

        public String getDJ_CONTENT_getCommonDictionaryListV10() {
            return DJ_CONTENT_getCommonDictionaryListV10;
        }

        public void setDJ_CONTENT_getCommonDictionaryListV10(String DJ_CONTENT_getCommonDictionaryListV10) {
            this.DJ_CONTENT_getCommonDictionaryListV10 = DJ_CONTENT_getCommonDictionaryListV10;
        }

        public String getDJ_CONTENT_getPartytechTopicListV10() {
            return DJ_CONTENT_getPartytechTopicListV10;
        }

        public void setDJ_CONTENT_getPartytechTopicListV10(String DJ_CONTENT_getPartytechTopicListV10) {
            this.DJ_CONTENT_getPartytechTopicListV10 = DJ_CONTENT_getPartytechTopicListV10;
        }

        public String getDJ_CONTENT_partytechTopicSaveAnswerV10() {
            return DJ_CONTENT_partytechTopicSaveAnswerV10;
        }

        public void setDJ_CONTENT_partytechTopicSaveAnswerV10(String DJ_CONTENT_partytechTopicSaveAnswerV10) {
            this.DJ_CONTENT_partytechTopicSaveAnswerV10 = DJ_CONTENT_partytechTopicSaveAnswerV10;
        }

        public String getDJ_CONTENT_partytechTopicSubmitExamV10() {
            return DJ_CONTENT_partytechTopicSubmitExamV10;
        }

        public void setDJ_CONTENT_partytechTopicSubmitExamV10(String DJ_CONTENT_partytechTopicSubmitExamV10) {
            this.DJ_CONTENT_partytechTopicSubmitExamV10 = DJ_CONTENT_partytechTopicSubmitExamV10;
        }

        public String getDJ_CONTENT_partytechTopicGiveUpExamV10() {
            return DJ_CONTENT_partytechTopicGiveUpExamV10;
        }

        public void setDJ_CONTENT_partytechTopicGiveUpExamV10(String DJ_CONTENT_partytechTopicGiveUpExamV10) {
            this.DJ_CONTENT_partytechTopicGiveUpExamV10 = DJ_CONTENT_partytechTopicGiveUpExamV10;
        }

        public String getDJ_CONTENT_partytechCourceListV10() {
            return DJ_CONTENT_partytechCourceListV10;
        }

        public void setDJ_CONTENT_partytechCourceListV10(String DJ_CONTENT_partytechCourceListV10) {
            this.DJ_CONTENT_partytechCourceListV10 = DJ_CONTENT_partytechCourceListV10;
        }

        public String getDJ_bussnessId() {
            return DJ_bussnessId;
        }

        public void setDJ_bussnessId(String DJ_bussnessId) {
            this.DJ_bussnessId = DJ_bussnessId;
        }

        public String getGetMenusByFileJson() {
            return getMenusByFileJson;
        }

        public void setGetMenusByFileJson(String getMenusByFileJson) {
            this.getMenusByFileJson = getMenusByFileJson;
        }

        public String getDJ_webMenuFjson() {
            return DJ_webMenuFjson;
        }

        public void setDJ_webMenuFjson(String DJ_webMenuFjson) {
            this.DJ_webMenuFjson = DJ_webMenuFjson;
        }

        public String getDJ_AndroidMenuFjson() {
            return DJ_AndroidMenuFjson;
        }

        public void setDJ_AndroidMenuFjson(String DJ_AndroidMenuFjson) {
            this.DJ_AndroidMenuFjson = DJ_AndroidMenuFjson;
        }

        public String getGetPartytechImagetextListV10() {
            return getPartytechImagetextListV10;
        }

        public void setGetPartytechImagetextListV10(String getPartytechImagetextListV10) {
            this.getPartytechImagetextListV10 = getPartytechImagetextListV10;
        }
    }
}
