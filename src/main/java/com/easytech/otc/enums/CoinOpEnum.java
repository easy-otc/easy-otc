package com.easytech.otc.enums;

import lombok.Getter;

@Getter
public enum CoinOpEnum {

                        BUY(0, "买"), SELL(1, "卖");

    int    code;
    String desc;

    CoinOpEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 币操作类型
     * <p>
     * 充提是主动流水，买卖是自动流水
     */
    @Getter
    public enum CoinOp {

                        RECHARGE(0, "充"), WITHDRAWAL(1, "提"), BUY(2, "买"), SELL(3, "卖");

        int    code;
        String desc;

        CoinOp(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

    }

    /**
     * 法币操作类型
     * <p>
     * 0-充，1-提，2-手续费，3-手续费返还，充提属于主动流水，手续费、手续费返还属于自动流水
     */
    @Getter
    public enum LegalOp {

                         RECHARGE(0, "充"), WITHDRAWAL(1, "提"), FEE(2, "手续费"), PAY_BACK_FEE(3, "手续费返还");

        int    code;
        String desc;

        LegalOp(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

    }
}
