
package com.easytech.otc.mvc.protocol;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 协议响应对象
 */
public interface ProtocolResponse {

    public final static SerializerFeature[] SERIAL_FEATURE = new SerializerFeature[] { SerializerFeature.UseISO8601DateFormat };

    /**
     * 转换成协议字符串
     *
     * @return
     */
    public String toPlainProtocol();

    /**
     * 非法参数
     */
    public ProtocolResponse setIllegalArgument();

    /**
     * 系统异常
     */
    public ProtocolResponse setSystemError();

    /**
     * 成功
     */
    public ProtocolResponse setSuccess();

    /**
     * 失败
     *
     * @param retCode
     */
    public ProtocolResponse setFail(int retCode);

    /**
     * 失败
     *
     * @param retCode
     * @param errorMsg
     * @return
     */
    public ProtocolResponse setFail(int retCode, String errorMsg);

    /**
     * 失败
     *
     * @param retCodeEnum
     * @return
     */
    public ProtocolResponse setFail(RetCodeEnum retCodeEnum);

}
