package com.easytech.otc.enums;

import lombok.Getter;

/**
 * 实名认证状态
 */
@Getter
public enum RealNameStatusEnum {

    COMMITED(0, "已提交"), VERIFYING(1, "审核中"), SUCCEED(2, "认证通过"), FAILED(3, "认证失败");

    int    code;
    String desc;

    RealNameStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
