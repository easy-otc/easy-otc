package com.easytech.otc.mvc.vo;

import lombok.Data;

@Data
public class LoginReturnVO {
    private String  userName; //用户名
    private Integer uid;      //用户id
    private String  token;    //token
}
