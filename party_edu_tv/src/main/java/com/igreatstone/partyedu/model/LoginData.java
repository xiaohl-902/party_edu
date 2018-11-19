package com.igreatstone.partyedu.model;

import java.util.List;

/**
 * Created by yy on 2017/11/21.
 */

public class LoginData {

    /**
     * msg : 登录成功
     * datas_androidWebURList : [{"ugroupNames":"南江","indextitlePic":"201711131356087595.png","ubusinessName":"党教业务系统","isDelete":"0","ubusinessId":"05051357441470000","urlIntro":"","url":"http://10.0.1.122:8080/getClassFile/pages/index22.jsp","wfUserInfo":"维护人员：蒋双全22\r\n电话：13545456253\r\n公司名：成都浩石科技有限公司\r\n地址：成都经开区孵化园","createTime":"2017-11-09 15:50:59","ugroupIds":"11071459147070001","contiletlPic":"201711131356083781.png","id":"11091550599100000","status":""}]
     * datas : [{"ugroupId":"11071459147070001","last_login_time":"2017-11-20 10:50:26","idCard":"","uareaId":"南江","ubusinessId":"05051357441470000","ugroupChId":"11071459147070001,","password":"","online_time":"","realUserid":"11150934033630003","online_update_time":"2017-11-20 10:50:26","partyName":"","auditUid":"1","ustationId":"","ugroupPIds":"06051356138690000,06051405021920000,11071458491480000,,11071459147070001","id":"11071500148520003","partyId":"","createDate":"2017-11-07 15:00:14","roleTeamId":"","isDelete":"0","loginToken":"11126d834032346d1bc97381383bf1dc0d4210f74c16b58dac9d71cd41910810014de7b10c12c0142577824ee5af76e6ddd6057df52f07e08b91f5410644b398bb4051fa4f6cd975420bc31500795906d1afc9d6044377791d9acfd11bcb984492f1990f7ec5cc19df07ce452cdf90b5c5ef6dfff56e6de4bcc08fa966e14b241d104b6eeda2551f4d9d3efa4a7d2a0b62fc19a0517ef2ca512fdedef1650dac3f14ec6fe2ee527858e285fdeb09a52235894c1d12d4b56298eefd53caff0ca0aaba767f2bb4399a","iptmp":"","realName":"南江区党支部","macAddress":"1.1.1","phoneNumber":"","auditUname":"admin1","headPortraitFilepath":"","userContentRight":"[{\"05181046333350000\":[\"11080918038180005\",\"11080919004270006\"],\"05102238304580001\":[\"1107172914410001\",\"11071737032650000\",\"11080910562570001\",\"11080933507020007\",\"11080934202670008\",\"110809342026700011\",\"110809342026700022\",\"110809342026700033\",\"110809342026700044\",\"110809342026700055\",\"110809342026700066\",\"110809342026700077\",\"110809342026700088\",\"110809342026700099\",\"110809342026700000\",\"1108093420267000111\",\"11080934202670008222\",\"11080934202670008221\",\"1108093420267000833\"],\"0510224313680003\":[\"1108091243320002\",\"09221002095010056\"],\"05102245506880004\":[\"17\",\"18\",\"19\",\"20\",\"21\",\"22\",\"23\",\"24\",\"25\",\"26\",\"27\",\"28\",\"29\",\"30\",\"31\",\"32\",\"33\",\"34\"],\"05261605426090000\":[\"11081123077850001\",\"11081144003340000\",\"11081509515460001\",\"11081153022650001\",\"11081154319460002\",\"11081518317650009\",\"11081158409770004\",\"1108115521450003\",\"11090951534340000\",\"11131452245570000\",\"11131453239650001\",\"11131455575910002\",\"1113145618730003\",\"11131459387900004\",\"11131501305430005\",\"11131502138870006\",\"11131504591920007\"],\"05181708193160000\":[\"11081543437070010\",\"11081544134620011\",\"11081543168810000\",\"11081349468870000\",\"11081358022000001\",\"11081402128070002\",\"11081402451380003\"]}]","name":"","auditStatus":"2","nikeName":"","loginTokenType":"","roleTeamName":""}]
     * siteName : 南江>>南江区党支部
     * uGroup : {"uGroupName":"南江","userType":"special","uGroup":[]}
     * status : 1
     */

    private String msg;
    private String siteName;
    private String uGroup;
    private int status;
    private List<DatasAndroidWebURListBean> datas_androidWebURList;
    private List<DatasBean> datas;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public List<DatasAndroidWebURListBean> getDatas_androidWebURList() {
        return datas_androidWebURList;
    }

    public void setDatas_androidWebURList(List<DatasAndroidWebURListBean> datas_androidWebURList) {
        this.datas_androidWebURList = datas_androidWebURList;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasAndroidWebURListBean {
        /**
         * ugroupNames : 南江
         * indextitlePic : 201711131356087595.png
         * ubusinessName : 党教业务系统
         * isDelete : 0
         * ubusinessId : 05051357441470000
         * urlIntro :
         * url : http://10.0.1.122:8080/getClassFile/pages/index22.jsp
         * wfUserInfo : 维护人员：蒋双全22
         电话：13545456253
         公司名：成都浩石科技有限公司
         地址：成都经开区孵化园
         * createTime : 2017-11-09 15:50:59
         * ugroupIds : 11071459147070001
         * contiletlPic : 201711131356083781.png
         * id : 11091550599100000
         * status :
         */

        private String ugroupNames;
        private String indextitlePic;
        private String ubusinessName;
        private String isDelete;
        private String ubusinessId;
        private String urlIntro;
        private String url;
        private String wfUserInfo;
        private String createTime;
        private String agentServerUrl;
        private String ugroupIds;
        private String contiletlPic;
        private String id;
        private String status;

        public String getUgroupNames() {
            return ugroupNames;
        }

        public void setUgroupNames(String ugroupNames) {
            this.ugroupNames = ugroupNames;
        }

        public String getIndextitlePic() {
            return indextitlePic;
        }

        public void setIndextitlePic(String indextitlePic) {
            this.indextitlePic = indextitlePic;
        }

        public String getUbusinessName() {
            return ubusinessName;
        }

        public void setUbusinessName(String ubusinessName) {
            this.ubusinessName = ubusinessName;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getUbusinessId() {
            return ubusinessId;
        }

        public void setUbusinessId(String ubusinessId) {
            this.ubusinessId = ubusinessId;
        }

        public String getUrlIntro() {
            return urlIntro;
        }

        public void setUrlIntro(String urlIntro) {
            this.urlIntro = urlIntro;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWfUserInfo() {
            return wfUserInfo;
        }

        public void setWfUserInfo(String wfUserInfo) {
            this.wfUserInfo = wfUserInfo;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }


        public String getAgentServerUrl() {
            return agentServerUrl;
        }

        public void setAgentServerUrl(String createTime) {
            this.agentServerUrl = agentServerUrl;
        }

        public String getUgroupIds() {
            return ugroupIds;
        }

        public void setUgroupIds(String ugroupIds) {
            this.ugroupIds = ugroupIds;
        }

        public String getContiletlPic() {
            return contiletlPic;
        }

        public void setContiletlPic(String contiletlPic) {
            this.contiletlPic = contiletlPic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class DatasBean {
        /**
         * ugroupId : 11071459147070001
         * last_login_time : 2017-11-20 10:50:26
         * idCard :
         * uareaId : 南江
         * ubusinessId : 05051357441470000
         * ugroupChId : 11071459147070001,
         * password :
         * online_time :
         * realUserid : 11150934033630003
         * online_update_time : 2017-11-20 10:50:26
         * partyName :
         * auditUid : 1
         * ustationId :
         * ugroupPIds : 06051356138690000,06051405021920000,11071458491480000,,11071459147070001
         * id : 11071500148520003
         * partyId :
         * createDate : 2017-11-07 15:00:14
         * roleTeamId :
         * isDelete : 0
         * loginToken : 11126d834032346d1bc97381383bf1dc0d4210f74c16b58dac9d71cd41910810014de7b10c12c0142577824ee5af76e6ddd6057df52f07e08b91f5410644b398bb4051fa4f6cd975420bc31500795906d1afc9d6044377791d9acfd11bcb984492f1990f7ec5cc19df07ce452cdf90b5c5ef6dfff56e6de4bcc08fa966e14b241d104b6eeda2551f4d9d3efa4a7d2a0b62fc19a0517ef2ca512fdedef1650dac3f14ec6fe2ee527858e285fdeb09a52235894c1d12d4b56298eefd53caff0ca0aaba767f2bb4399a
         * iptmp :
         * realName : 南江区党支部
         * macAddress : 1.1.1
         * phoneNumber :
         * auditUname : admin1
         * headPortraitFilepath :
         * userContentRight : [{"05181046333350000":["11080918038180005","11080919004270006"],"05102238304580001":["1107172914410001","11071737032650000","11080910562570001","11080933507020007","11080934202670008","110809342026700011","110809342026700022","110809342026700033","110809342026700044","110809342026700055","110809342026700066","110809342026700077","110809342026700088","110809342026700099","110809342026700000","1108093420267000111","11080934202670008222","11080934202670008221","1108093420267000833"],"0510224313680003":["1108091243320002","09221002095010056"],"05102245506880004":["17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34"],"05261605426090000":["11081123077850001","11081144003340000","11081509515460001","11081153022650001","11081154319460002","11081518317650009","11081158409770004","1108115521450003","11090951534340000","11131452245570000","11131453239650001","11131455575910002","1113145618730003","11131459387900004","11131501305430005","11131502138870006","11131504591920007"],"05181708193160000":["11081543437070010","11081544134620011","11081543168810000","11081349468870000","11081358022000001","11081402128070002","11081402451380003"]}]
         * name :
         * auditStatus : 2
         * nikeName :
         * loginTokenType :
         * roleTeamName :
         */

        private String ugroupId;
        private String last_login_time;
        private String idCard;
        private String uareaId;
        private String ubusinessId;
        private String ugroupChId;
        private String password;
        private String online_time;
        private String realUserid;
        private String online_update_time;
        private String partyName;
        private String auditUid;
        private String ustationId;
        private String ugroupPIds;
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
        private String loginTokenType;
        private String roleTeamName;

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

        public String getUareaId() {
            return uareaId;
        }

        public void setUareaId(String uareaId) {
            this.uareaId = uareaId;
        }

        public String getUbusinessId() {
            return ubusinessId;
        }

        public void setUbusinessId(String ubusinessId) {
            this.ubusinessId = ubusinessId;
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

        public String getOnline_time() {
            return online_time;
        }

        public void setOnline_time(String online_time) {
            this.online_time = online_time;
        }

        public String getRealUserid() {
            return realUserid;
        }

        public void setRealUserid(String realUserid) {
            this.realUserid = realUserid;
        }

        public String getOnline_update_time() {
            return online_update_time;
        }

        public void setOnline_update_time(String online_update_time) {
            this.online_update_time = online_update_time;
        }

        public String getPartyName() {
            return partyName;
        }

        public void setPartyName(String partyName) {
            this.partyName = partyName;
        }

        public String getAuditUid() {
            return auditUid;
        }

        public void setAuditUid(String auditUid) {
            this.auditUid = auditUid;
        }

        public String getUstationId() {
            return ustationId;
        }

        public void setUstationId(String ustationId) {
            this.ustationId = ustationId;
        }

        public String getUgroupPIds() {
            return ugroupPIds;
        }

        public void setUgroupPIds(String ugroupPIds) {
            this.ugroupPIds = ugroupPIds;
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

        public String getLoginTokenType() {
            return loginTokenType;
        }

        public void setLoginTokenType(String loginTokenType) {
            this.loginTokenType = loginTokenType;
        }

        public String getRoleTeamName() {
            return roleTeamName;
        }

        public void setRoleTeamName(String roleTeamName) {
            this.roleTeamName = roleTeamName;
        }
    }
}
