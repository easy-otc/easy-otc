package com.easytech.otc.enums;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/20 22:14
 */

public enum VerifyCodeEnum {
                            REGISTER(1, "注册短信");
    private int    type;
    private String desc;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    VerifyCodeEnum() {
    }

    VerifyCodeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
