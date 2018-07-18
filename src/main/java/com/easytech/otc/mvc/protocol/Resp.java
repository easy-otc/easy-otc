package com.easytech.otc.mvc.protocol;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 协议响应对象
 */
public class Resp<E> implements Serializable, ProtocolResponse {

    /** */
    private final static long serialVersionUID = 6725048659996675209L;

    @JSONField(name = "r", deserialize = false)
    private int retCode = RetCodeEnum.UNKOWN.getCode();

    // 数据, 具体接口, 具体约定
    @JSONField(name = "d", deserialize = false)
    private E data = null;

    // 错误信息
    @JSONField(name = "m", deserialize = false)
    private String errorMsg = null;

    public Resp() {

    }

    public Resp(E data) {
        this.data = (E) data;
    }

    /**
     * Getter method for property <tt>retCode</tt>.
     *
     * @return property value of retCode
     * @see RetCodeEnum
     */
    public int getRetCode() {
        return retCode;
    }

    /**
     * Setter method for property <tt>retCode</tt>.
     *
     * @param retCode value to be assigned to property retCode
     */
    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    /**
     * Getter method for property <tt>data</tt>.
     *
     * @return property value of data
     */
    public E getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     *
     * @param data value to be assigned to property data
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Getter method for property <tt>errorMsg</tt>.
     *
     * @return property value of errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Setter method for property <tt>errorMsg</tt>.
     *
     * @param errorMsg value to be assigned to property errorMsg
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static <T> Resp<T> newSuccessResult(T data) {
        Resp<T> result = new Resp<T>();
        result.setData(data);
        result.setRetCode(RetCodeEnum.SUCCESS.getCode());
        return result;
    }

    public static <T> Resp<T> newSuccessResult() {
        return newSuccessResult(null);
    }

    /**
     * @return
     */
    @Override
    public String toPlainProtocol() {
        return JSONObject.toJSONString(this, ProtocolResponse.SERIAL_FEATURE);
    }

    /**
     * @return
     */
    @Override
    public Resp<E> setIllegalArgument() {

        this.retCode = RetCodeEnum.ILLEGAL_ARGUMENT.getCode();

        return this;
    }

    /**
     * @return
     */
    @Override
    public Resp<E> setSystemError() {

        this.retCode = RetCodeEnum.INTERNAL_ERROR.getCode();

        return this;
    }

    /**
     * @return
     */
    @Override
    public Resp<E> setSuccess() {

        this.retCode = RetCodeEnum.SUCCESS.getCode();

        return this;
    }

    /**
     * @param retCode
     * @return
     */
    @Override
    public Resp<E> setFail(int retCode) {

        if (retCode > 0) {
            retCode = RetCodeEnum.FAIL.getCode();
        }

        this.retCode = retCode;
        return this;
    }

    /**
     * @param retCode
     * @param errorMsg
     * @return
     */
    @Override
    public Resp<E> setFail(int retCode, String errorMsg) {
        this.retCode = retCode;
        this.errorMsg = errorMsg;
        return this;
    }

    /**
     * @param retCode
     * @param errorMsg
     * @return
     */
    @Override
    public Resp<E> setFail(RetCodeEnum retCodeEnum) {
        this.retCode = retCodeEnum.getCode();
        this.errorMsg = retCodeEnum.getDesc();
        return this;
    }
}
