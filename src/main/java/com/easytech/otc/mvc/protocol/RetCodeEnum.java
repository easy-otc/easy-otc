package com.easytech.otc.mvc.protocol;

import lombok.Getter;

/**
 * API返回码
 * <pre>
 * 返回码由6位数字组成。当出现风险事件时，会在返回码上打个风险标记，一般是在6位数字前加上9
 * 比如-100002 登陆失败，当出现风险事件时，会返回-9100002
 *
 * <li> >0,表示业务成功。1是通用的业务成功</li>
 * <li> =0,应该不存在此类返回码</li>
 * <li> <0,表示业务失败。-1是通用的业务失败</li>
 * </pre>
 */
@Getter
public enum RetCodeEnum {

    /**
     * 未知异常
     */
    UNKOWN(000000, "未知异常"),
    /**
     * 正常状态, 请求被正确处理,并且获得预期的结果
     */
    SUCCESS(000001, "成功"),
    /**
     * 失败
     */
    FAIL(-000001, "失败"),
    /**
     * 业务异常
     */
    BUSINESS_ERROR(-100001, "业务异常"),
    /**
     * 短信验证码错误
     */
    VERIFY_CODE_ERROR(-100002, "短信验证码错误"),
    /**
     * 注册失败，用户名已存在
     */
    NAME_REPEAT_ERROR(-100003, "注册失败，用户名已存在"),
    /**
     * 注册失败，手机号码已存在
     */
    MOBILE_ERROR(-100004, "注册失败，手机号码已存在"),
    /**
     * 邀请码错误
     */
    INVITE_ERROR(-100005,"邀请码错误"),
    LOGIN_ERROR(-100020,"登录失败，账号或密码错误"),
    /***用户实名认证失败**/
    USER_REAL_AUTH_FAILED(-100008, "用户实名认证失败"),
    /***交易密码不正确**/
    TRADE_PAY_PASSWORD_ERROR(-100009, "交易密码不正确"),
    /**
     * 充值功能暂停服务
     **/
    RECHARGE_CLOSE(-100012, "充值功能暂停服务"),
    /**
     * 提现功能暂停服务
     **/
    WITHDRAWAL_CLOSE(-100013, "提现功能暂停服务"),
    /**
     * 用户冻结
     **/
    USER_FROZEN(-100014, "用户账户已冻结，请联系客服处理"),


    /**
     * 身份未验证
     */
    UNAUTHORIZED(-200002, "身份未验证"),

    /**
     * 拒绝访问.主要是一些非法访问<br>
     */
    FORBIDDEN(-200003, "拒绝访问"),
    /**
     * 非法请求参数
     */
    ILLEGAL_ARGUMENT(-300001, "非法请求参数"),
    /**
     * 非法请求参数
     */
    NO_IDEMPOTENT(-300002, "缺少幂等标识"),

    /**
     * 内部异常
     */
    INTERNAL_ERROR(-999999, "内部异常");

    RetCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    private int code;
    private String desc;

}
