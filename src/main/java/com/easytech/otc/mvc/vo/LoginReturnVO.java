package com.easytech.otc.mvc.vo;

import lombok.Data;

@Data
public class LoginReturnVO {
    private String  userName;    //用户名
    private Integer uid;         //用户id
    private String  mobile;      //手机号
    private String  email;       //邮箱
    private String  invitionCode;//邀请码
    private Integer invitedBy;   //邀请人
    private String  token;       //token
}
