package com.easytech.otc.mvc.controller.api;

import java.util.Objects;

import com.easytech.otc.cache.UserKey;
import com.easytech.otc.common.WebTool;
import com.easytech.otc.mvc.protocol.*;
import com.easytech.otc.mvc.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.easytech.otc.cache.CodeKey;
import com.easytech.otc.common.PasswdUtil;
import com.easytech.otc.enums.VerifyCodeEnum;
import com.easytech.otc.manager.redis.support.RedisTool;
import com.easytech.otc.mapper.model.User;
import com.easytech.otc.mvc.controller.WebConst;
import com.easytech.otc.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 20:39
 */
@RestController
@RequestMapping(WebConst.API_V1_PREFIX + "/passport")
public class PassportController {

    @Autowired
    private RedisTool    redisTool;
    @Autowired
    private UserService  userService;

    @Autowired
    AuthedInfoRepository authedInfoRepository;

    @PostMapping(value = "/login")
    @ACL(authControl = false)
    public Resp<LoginReturnVO> login(HttpServletRequest request,@RequestBody LoginRequest loginRequest) {
        Resp<LoginReturnVO> result = new Resp<>();
        userService.checkLoginRequest(loginRequest);
        User user = userService.getUserByMobile(loginRequest.getMobile());
        if (user == null) {
            return result.setFail(RetCodeEnum.LOGIN_ERROR);
        }
        if (!PasswdUtil.verify(loginRequest.getPassword(), user.getLoginPassword())) {
            return result.setFail(RetCodeEnum.LOGIN_ERROR);
        }
        userService.login(user, WebTool.getIpAddr(request));
        LoginReturnVO loginReturnVO = authedInfoRepository.saveLogin(user);
        return Resp.newSuccessResult(loginReturnVO);
    }

    @PostMapping(value = "/register")
    @ACL(authControl = false)
    public Resp<RegisterVO> register(@RequestBody RegisterRequest registerRequest) {
        Resp<RegisterVO> result = new Resp<>();

        String verifyCode = redisTool.hget(CodeKey.VERIFY_CODE, VerifyCodeEnum.NO_LOGIN, registerRequest.getMobile());
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
        userService.register(registerRequest);
        return Resp.newSuccessResult();
    }

    /**
     * 修改密码
     * @return
     */
    @PostMapping(value = "/passport/{uid}")
    @ACL
    public Resp updatePassword(@PathVariable Integer uid,@RequestBody UpdatePassportRequest pssportRequest){
        Resp resp = new Resp();
        User user = userService.getById(uid);
        if(user==null){
            resp.setFail(RetCodeEnum.ILLEGAL_ARGUMENT);
            return resp;
        }
        if (!PasswdUtil.verify(pssportRequest.getOldPassword(), user.getLoginPassword())) {
            return resp.setFail(RetCodeEnum.ILLEGAL_ARGUMENT);
        }
        userService.updatePassword(uid,pssportRequest.getNewPassword());
        AuthedInfo authedInfo = RequestContext.getAuthedInfo();
        redisTool.del(UserKey.USER_INFO,authedInfo.getToken());
        RequestContext.clear();
        return Resp.newSuccessResult();
    }



}