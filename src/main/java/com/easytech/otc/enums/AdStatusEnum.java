package com.easytech.otc.enums;

import lombok.Getter;

@Getter
public enum AdStatusEnum {

    ON(0, "上单"), OFF(1, "撤单"), DELETE(2, "删除");

    int code;
    String desc;

    AdStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
