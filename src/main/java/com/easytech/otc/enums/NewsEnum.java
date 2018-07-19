package com.easytech.otc.enums;

import lombok.Getter;

public class NewsEnum {

    ;

    /**
     * 讯息类型
     */
    @Getter
    public enum Type {

                      TRADE(0, "公告"), SUGGEST(3, "新闻");

        int    code;
        String desc;

        Type(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

    /**
     * 讯息状态
     */
    @Getter
    public enum Status {

                        PUBLISH(0, "发布"), CANCELL(1, "撤销");

        int    code;
        String desc;

        Status(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
}
