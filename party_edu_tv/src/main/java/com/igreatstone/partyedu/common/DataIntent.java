package com.igreatstone.partyedu.common;

import java.util.List;

/**
 * Created by yy on 2017/8/9.
 */

public class DataIntent {

    /**
     * package : 目标APK包名
     * action : action  动作
     * category : category  分类
     * data : dataURI  数据
     * extras : {"key0":"value0","key1":"value1","key2":"value2"}
     * flags : ["NEW_TASK","SINGLE_TOP","SINGLE_INSTANCE","SINGLE_TASK"]
     */

    private String packageX;
    private String action;
    private String category;
    private String data;
    private ExtrasBean extras;
    private List<String> flags;

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ExtrasBean getExtras() {
        return extras;
    }

    public void setExtras(ExtrasBean extras) {
        this.extras = extras;
    }

    public List<String> getFlags() {
        return flags;
    }

    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    public static class ExtrasBean {
        /**
         * key0 : value0
         * key1 : value1
         * key2 : value2
         */

        private String key0;
        private String key1;
        private String key2;

        public String getKey0() {
            return key0;
        }

        public void setKey0(String key0) {
            this.key0 = key0;
        }

        public String getKey1() {
            return key1;
        }

        public void setKey1(String key1) {
            this.key1 = key1;
        }

        public String getKey2() {
            return key2;
        }

        public void setKey2(String key2) {
            this.key2 = key2;
        }
    }
}
