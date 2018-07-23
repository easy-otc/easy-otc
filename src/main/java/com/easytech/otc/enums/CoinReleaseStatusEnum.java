package com.easytech.otc.enums;

/**
 * 放币状态，
 *
 * 0-未放币
 * 1-已放币，表示用户点击放币按钮
 * 2-币交易接口已调用
 * 3-币交易上链成功
 */
public enum CoinReleaseStatusEnum {

    NOT_YET(0, "未放币"), RELEASED(1, "已放币"), INVOKE_RELEASE_API_FAILED(2, "币交易接口调用失败"), INVOKEED_RELEASE_API(3, "币交易接口已调用"), TX_ON_NET(4, "币交易已提交到网络"), TX_ON_CHAIN(5, "币交易上链成功");

    int    code;
    String desc;

    CoinReleaseStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
