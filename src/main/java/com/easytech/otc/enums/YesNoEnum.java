package com.easytech.otc.enums;

import lombok.Getter;

@Getter
public enum YesNoEnum {

                       NO(0, "否"), YES(1, "是");

    int    code;
    String desc;

    YesNoEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
