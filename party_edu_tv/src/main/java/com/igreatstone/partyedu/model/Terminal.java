package com.igreatstone.partyedu.model;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.StringUtils;
import com.igreatstone.partyedu.BuildConfig;
import com.igreatstone.partyedu.common.CommonUtil;

/**
 * 终端信息
 */
public class Terminal {
    private String type = BuildConfig.TERMINAL_TYPE;// 终端类型
    private String typeCode = "Android";//DeviceUtils.getModel();// 终端型号 统一的用Android
    private String typeName = DeviceUtils.getManufacturer();// 型号名称
    private String macAddr = CommonUtil.getMac();//mac地址

    public String getType() {
        if(StringUtils.isEmpty(type)){
            return "";
        }
        return type.toUpperCase();
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }
}
