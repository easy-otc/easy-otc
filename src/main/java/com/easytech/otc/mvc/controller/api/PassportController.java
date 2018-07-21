package com.easytech.otc.mvc.controller.api;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easytech.otc.cache.CodeKey;
import com.easytech.otc.cache.DemoKey;
import com.easytech.otc.enums.VerifyCodeEnum;
import com.easytech.otc.manager.redis.support.RedisTool;
import com.easytech.otc.mvc.controller.WebConst;
import com.easytech.otc.mvc.protocol.ACL;
import com.easytech.otc.mvc.protocol.Resp;
import com.easytech.otc.mvc.protocol.RetCodeEnum;
import com.easytech.otc.mvc.vo.LoginRequest;
import com.easytech.otc.mvc.vo.LoginReturnVO;
import com.easytech.otc.mvc.vo.RegisterRequest;
import com.easytech.otc.mvc.vo.RegisterVO;
import com.easytech.otc.service.UserService;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 20:39
 */
@RestController
@RequestMapping(WebConst.API_V1_PREFIX + "/passport")
public class PassportController {

    @Autowired
    private RedisTool   redisTool;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    @ACL(authControl = false)
    public Resp<LoginReturnVO> login(@RequestBody LoginRequest loginRequest) {
        Resp<LoginReturnVO> result = new Resp<>();
        redisTool.get(DemoKey.demo, "1");
        return result;
    }

    @PostMapping(value = "/register")
    @ACL(authControl = false)
    public Resp<RegisterVO> register(@RequestBody RegisterRequest registerRequest) {
        Resp<RegisterVO> result = new Resp<>();

        String verifyCode = redisTool.hget(CodeKey.VERIFY_CODE, VerifyCodeEnum.REGISTER, registerRequest.getMobile());
        if (!Objects.equals(verifyCode, registerRequest.getVerifyCode())) {
            return result.setFail(RetCodeEnum.VERIFY_CODE_ERROR);
        }
        if (userService.mobileExists(registerRequest.getMobile())) {

        }
        if (userService.nameExists(registerRequest.getUserName())) {

        }

        return result;
    }

}
