package com.igreatstone.partyedu.model;

import java.util.List;

/**
 * Created by yy on 2017/8/9.
 */

public class DataIntent {

    /**
     * packageX :
     * activity :
     * action : action
     * category : category
     * data : data
     * extras : {"key0":"value0","key1":"value1","key2":"value2"}
     * flags : ["NEW_TASK","SINGLE_TOP","SINGLE_INSTANCE","SINGLE_TASK"]
     */

    private String packageX;
    private String activity;
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

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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

        public String getKey0() {
            return key0;
        }

        public void setKey0(String key0) {
            this.key0 = key0;
        }

    }
}
