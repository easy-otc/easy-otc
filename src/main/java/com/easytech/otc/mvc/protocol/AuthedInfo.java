package com.easytech.otc.mvc.protocol;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户已验证身份
 */
@Data
public class AuthedInfo implements Serializable {

    /** */
    private static final long serialVersionUID = -2813504955609506749L;

    /**
     * 已验证的身份Token
     */
    private String            token;

    /**
     * 身份信息
     */
    private String            uid;

    /**
     * 创建时间
     */
    private Date              createTime;

    /**
     * 过期时间
     */
    private Date              expireTime;

    /**
     * 附加信息
     */
    private String            attach;
}
