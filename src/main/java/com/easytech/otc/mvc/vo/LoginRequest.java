package com.easytech.otc.mvc.vo;

import lombok.Data;

@Data
public class LoginRequest {
    private String mobile;  //手机号码
    private String password;//密文
}
