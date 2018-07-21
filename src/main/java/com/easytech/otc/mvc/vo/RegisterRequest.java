package com.easytech.otc.mvc.vo;

import lombok.Data;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:08
 */
@Data
public class RegisterRequest {
    private String userName;    //用户名
    private String mobile;      //手机号码
    private String ciphertext;  //密文
    private String verifyCode;  //验证码
    private String invitionCode;//邀请码
}
