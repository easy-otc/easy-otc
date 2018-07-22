package com.easytech.otc.mvc.protocol;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户已验证身份
 */
@Data
public class AuthedInfo implements Serializable {

    /** */
    private static final long serialVersionUID = -2813504955609506749L;
    /**
     * token
     */
    private String            token;
    /**
     * 用户名
     */
    private String            userName;
    /**
     * 用户id
     */
    private Integer           uid;
    /**
     * 手机号
     */
    private String            mobile;
    /**
     * 邮箱
     */
    private String            email;
    /**
     * 邀请码
     */
    private String            invitionCode;
    /**
     * 邀请人
     */
    private Integer           invitedBy;

}
