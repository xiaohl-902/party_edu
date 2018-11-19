package com.igreatstone.partyedu.model;

import java.util.List;

/**
 * Created by yy on 2017/12/13.
 */

public class NoLoginData {

    /**
     * msg : 登录成功
     * datas_androidWebURList : 1
     * datas : [{"ugroupId":"94","last_login_time":"2017-12-13 10:34:52","idCard":"","ubusinessId":"05051357441470000","uareaId":"","ugroupChId":"0","password":"1","realUserid":"","online_time":"2017-05-20 08:13:22","online_update_time":"2017-12-13 10:34:52","auditUid":"","partyName":"","ugroupPIds":"06051356138690000,06051405021920000,,06051405179280001,87,88,94","ustationId":"","id":"2967","partyId":"","createDate":"2015-12-29 14:26:49","roleTeamId":"","isDelete":"0","loginToken":"11126d834032346d1bca7b87382de8994e1656880b4bf0cdd5ca6cc60dd64a4e3450e4a1353f9e09256ad20cb7ff0bb7dec01666a76d44b9d5c5a11e3d4fe5dac2115be859738d30135ebb450d6e8cbb766e542b561c7c620b80d2f707dcb41a8fa6c8483a89894f8958dd5d6d87c6f193b02fb59a637cf2ef89a2895ee814714a0d4068e9a2520f56961efd0934604055b951ff043fc4d65038b080c9537cc16150b124e9e5","iptmp":"","realName":"华坪办华坪办事处","macAddress":"d8:47:10:6a:c8:e8","phoneNumber":"","auditUname":"admin1","headPortraitFilepath":"","userContentRight":"[{}]","name":"myjy001","auditStatus":"2","nikeName":"","roleTeamName":"","loginTokenType":"2"}]
     * siteName : d8:47:10:6a:c8:e8
     * uGroup : {"uGroupName":"华坪办"}
     * status : 1
     */

    private String msg;
    private String datas_androidWebURList;
    private String siteName;
    private String uGroup;
    private int status;
    private List<DatasBean> datas;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDatas_androidWebURList() {
        return datas_androidWebURList;
    }

    public void setDatas_androidWebURList(String datas_androidWebURList) {
        this.datas_androidWebURList = datas_androidWebURList;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUGroup() {
        return uGroup;
    }

    public void setUGroup(String uGroup) {
        this.uGroup = uGroup;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * ugroupId : 94
         * last_login_time : 2017-12-13 10:34:52
         * idCard :
         * ubusinessId : 05051357441470000
         * uareaId :
         * ugroupChId : 0
         * password : 1
         * realUserid :
         * online_time : 2017-05-20 08:13:22
         * online_update_time : 2017-12-13 10:34:52
         * auditUid :
         * partyName :
         * ugroupPIds : 06051356138690000,06051405021920000,,06051405179280001,87,88,94
         * ustationId :
         * id : 2967
         * partyId :
         * createDate : 2015-12-29 14:26:49
         * roleTeamId :
         * isDelete : 0
         * loginToken : 11126d834032346d1bca7b87382de8994e1656880b4bf0cdd5ca6cc60dd64a4e3450e4a1353f9e09256ad20cb7ff0bb7dec01666a76d44b9d5c5a11e3d4fe5dac2115be859738d30135ebb450d6e8cbb766e542b561c7c620b80d2f707dcb41a8fa6c8483a89894f8958dd5d6d87c6f193b02fb59a637cf2ef89a2895ee814714a0d4068e9a2520f56961efd0934604055b951ff043fc4d65038b080c9537cc16150b124e9e5
         * iptmp :
         * realName : 华坪办华坪办事处
         * macAddress : d8:47:10:6a:c8:e8
         * phoneNumber :
         * auditUname : admin1
         * headPortraitFilepath :
         * userContentRight : [{}]
         * name : myjy001
         * auditStatus : 2
         * nikeName :
         * roleTeamName :
         * loginTokenType : 2
         */

        private String ugroupId;
        private String last_login_time;
        private String idCard;
        private String ubusinessId;
        private String uareaId;
        private String ugroupChId;
        private String password;
        private String realUserid;
        private String online_time;
        private String online_update_time;
        private String auditUid;
        private String partyName;
        private String ugroupPIds;
        private String ustationId;
        private String id;
        private String partyId;
        private String createDate;
        private String roleTeamId;
        private String isDelete;
        private String loginToken;
        private String iptmp;
        private String realName;
        private String macAddress;
        private String phoneNumber;
        private String auditUname;
        private String headPortraitFilepath;
        private String userContentRight;
        private String name;
        private String auditStatus;
        private String nikeName;
        private String roleTeamName;
        private String loginTokenType;

        public String getUgroupId() {
            return ugroupId;
        }

        public void setUgroupId(String ugroupId) {
            this.ugroupId = ugroupId;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getUbusinessId() {
            return ubusinessId;
        }

        public void setUbusinessId(String ubusinessId) {
            this.ubusinessId = ubusinessId;
        }

        public String getUareaId() {
            return uareaId;
        }

        public void setUareaId(String uareaId) {
            this.uareaId = uareaId;
        }

        public String getUgroupChId() {
            return ugroupChId;
        }

        public void setUgroupChId(String ugroupChId) {
            this.ugroupChId = ugroupChId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRealUserid() {
            return realUserid;
        }

        public void setRealUserid(String realUserid) {
            this.realUserid = realUserid;
        }

        public String getOnline_time() {
            return online_time;
        }

        public void setOnline_time(String online_time) {
            this.online_time = online_time;
        }

        public String getOnline_update_time() {
            return online_update_time;
        }

        public void setOnline_update_time(String online_update_time) {
            this.online_update_time = online_update_time;
        }

        public String getAuditUid() {
            return auditUid;
        }

        public void setAuditUid(String auditUid) {
            this.auditUid = auditUid;
        }

        public String getPartyName() {
            return partyName;
        }

        public void setPartyName(String partyName) {
            this.partyName = partyName;
        }

        public String getUgroupPIds() {
            return ugroupPIds;
        }

        public void setUgroupPIds(String ugroupPIds) {
            this.ugroupPIds = ugroupPIds;
        }

        public String getUstationId() {
            return ustationId;
        }

        public void setUstationId(String ustationId) {
            this.ustationId = ustationId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPartyId() {
            return partyId;
        }

        public void setPartyId(String partyId) {
            this.partyId = partyId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getRoleTeamId() {
            return roleTeamId;
        }

        public void setRoleTeamId(String roleTeamId) {
            this.roleTeamId = roleTeamId;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getLoginToken() {
            return loginToken;
        }

        public void setLoginToken(String loginToken) {
            this.loginToken = loginToken;
        }

        public String getIptmp() {
            return iptmp;
        }

        public void setIptmp(String iptmp) {
            this.iptmp = iptmp;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getMacAddress() {
            return macAddress;
        }

        public void setMacAddress(String macAddress) {
            this.macAddress = macAddress;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAuditUname() {
            return auditUname;
        }

        public void setAuditUname(String auditUname) {
            this.auditUname = auditUname;
        }

        public String getHeadPortraitFilepath() {
            return headPortraitFilepath;
        }

        public void setHeadPortraitFilepath(String headPortraitFilepath) {
            this.headPortraitFilepath = headPortraitFilepath;
        }

        public String getUserContentRight() {
            return userContentRight;
        }

        public void setUserContentRight(String userContentRight) {
            this.userContentRight = userContentRight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(String auditStatus) {
            this.auditStatus = auditStatus;
        }

        public String getNikeName() {
            return nikeName;
        }

        public void setNikeName(String nikeName) {
            this.nikeName = nikeName;
        }

        public String getRoleTeamName() {
            return roleTeamName;
        }

        public void setRoleTeamName(String roleTeamName) {
            this.roleTeamName = roleTeamName;
        }

        public String getLoginTokenType() {
            return loginTokenType;
        }

        public void setLoginTokenType(String loginTokenType) {
            this.loginTokenType = loginTokenType;
        }
    }
}
