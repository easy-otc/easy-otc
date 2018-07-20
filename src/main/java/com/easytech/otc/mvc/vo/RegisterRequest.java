package com.easytech.otc.mvc.vo;

import lombok.Getter;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:08
 */
@Getter
public class RegisterRequest {
    private String userName;  //用户名
    private String mobile;    //手机号码
    private String ciphertext;//密文
    private String verifyCode;//验证码
    private String invitionCode;//邀请码
}
