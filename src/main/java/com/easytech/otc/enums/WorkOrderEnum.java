package com.easytech.otc.enums;

import lombok.Getter;

public enum WorkOrderEnum {
    ;

    /**
     * 工单类型
     */
    @Getter
    public enum Type {

        TRADE(0, "交易"), ACCOUNT(1, "账户"), COMPLAINT(2, "投诉"), SUGGEST(3, "建议");

        int code;
        String desc;

        Type(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

    /**
     * 工单状态
     */
    @Getter
    public enum Status {

        COMMITED(0, "提交"), PROCESSING(1, "处理中"), FINISHED(2, "完成");

        int code;
        String desc;

        Status(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
}
