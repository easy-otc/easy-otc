package com.easytech.otc.request;

import com.alibaba.fastjson.JSON;
import com.easytech.otc.mvc.vo.LoginRequest;
import com.easytech.otc.mvc.vo.RegisterRequest;
import org.junit.Test;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/21 12:08
 */

public class RequestJsonTest {

    @Test
    public void registerRequestTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("陈诚豪");
        registerRequest.setMobile("13127898836");
        registerRequest.setVerifyCode("123456");
        registerRequest.setInvitionCode("abcdef");
        registerRequest.setCiphertext("aaaaaaa");
        System.out.println(JSON.toJSONString(registerRequest));
    }

    @Test
    public void loginRequestTest(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setMobile("13127898831");
        loginRequest.setPassword("123456789");
        System.out.println(JSON.toJSONString(loginRequest));
    }
}
