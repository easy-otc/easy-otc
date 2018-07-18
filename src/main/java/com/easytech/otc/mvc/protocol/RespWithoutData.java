/**
 * Copyright (C), 2011-2016, 微贷网.
 */
package com.easytech.otc.mvc.protocol;

/**
 * 没有数据的响应对象
 */
public class RespWithoutData extends Resp<Void> {

    /** */
    private static final long serialVersionUID = 1469559479337729805L;

    public RespWithoutData() {

    }

    /**
     * @param data
     */
    @Override
    public void setData(Void data) {
        super.setData(null);
    }
}
