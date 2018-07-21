package com.easytech.otc.mvc.controller.api;

import com.easytech.otc.cache.CodeKey;
import com.easytech.otc.common.PasswdUtil;
import com.easytech.otc.enums.VerifyCodeEnum;
import com.easytech.otc.manager.redis.support.RedisTool;
import com.easytech.otc.mapper.model.User;
import com.easytech.otc.mvc.controller.WebConst;
import com.easytech.otc.mvc.protocol.ACL;
import com.easytech.otc.mvc.protocol.AuthedInfoRepository;
import com.easytech.otc.mvc.protocol.Resp;
import com.easytech.otc.mvc.protocol.RetCodeEnum;
import com.easytech.otc.mvc.vo.LoginRequest;
import com.easytech.otc.mvc.vo.LoginReturnVO;
import com.easytech.otc.mvc.vo.RegisterRequest;
import com.easytech.otc.mvc.vo.RegisterVO;
import com.easytech.otc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 20:39
 */
@RestController
@RequestMapping(WebConst.API_V1_PREFIX + "/passport")
public class PassportController {

    @Autowired
    private RedisTool redisTool;
    @Autowired
    private UserService userService;

    @Autowired
    AuthedInfoRepository authedInfoRepository;

    @PostMapping(value = "/login")
    @ACL(authControl = false)
    public Resp<LoginReturnVO> login(@RequestBody LoginRequest loginRequest) {
        Resp<LoginReturnVO> result = new Resp<>();
        userService.checkLoginRequest(loginRequest);
        User user = userService.getUserByMobile(loginRequest.getMobile());
        if (user == null) {
            return result.setFail(RetCodeEnum.LOGIN_ERROR);
        }
        if (!PasswdUtil.verify(loginRequest.getPassword(), user.getLoginPassword())) {
            return result.setFail(RetCodeEnum.LOGIN_ERROR);
        }
        LoginReturnVO loginReturnVO = authedInfoRepository.saveLogin(user);
        return Resp.newSuccessResult(loginReturnVO);
    }

    @PostMapping(value = "/register")
    @ACL(authControl = false)
    public Resp<RegisterVO> register(@RequestBody RegisterRequest registerRequest) {
        Resp<RegisterVO> result = new Resp<>();

        String verifyCode = redisTool.hget(CodeKey.VERIFY_CODE, VerifyCodeEnum.REGISTER, registerRequest.getMobile());
        if (!Objects.equals(verifyCode, registerRequest.getVerifyCode())) {
            return result.setFail(RetCodeEnum.VERIFY_CODE_ERROR);
        }

        userService.checkRegisterRequest(registerRequest);
        if (userService.mobileExists(registerRequest.getMobile())) {
            return result.setFail(RetCodeEnum.MOBILE_ERROR);
        }
        if (userService.nameExists(registerRequest.getUserName())) {
            return result.setFail(RetCodeEnum.NAME_REPEAT_ERROR);
        }
        int uid = userService.register(registerRequest);
        userService.updateInvitionCode(uid);
        return Resp.newSuccessResult();


    }
}
