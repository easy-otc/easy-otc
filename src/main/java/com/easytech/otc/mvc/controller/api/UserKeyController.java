package com.easytech.otc.mvc.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.easytech.otc.cache.CodeKey;
import com.easytech.otc.common.MobileVerifyUtil;
import com.easytech.otc.common.MsgTool;
import com.easytech.otc.common.crypt.RSAUtils;
import com.easytech.otc.enums.VerifyCodeEnum;
import com.easytech.otc.exception.BizException;
import com.easytech.otc.manager.redis.support.RedisTool;
import com.easytech.otc.mvc.controller.WebConst;
import com.easytech.otc.mvc.protocol.ACL;
import com.easytech.otc.mvc.protocol.Resp;
import com.easytech.otc.mvc.protocol.RetCodeEnum;
import com.easytech.otc.service.UserService;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 20:36
 */
@RestController
@RequestMapping(WebConst.API_V1_PREFIX + "/user/key")
public class UserKeyController {

    @Autowired
    private RedisTool redisTool;


    @Autowired
    private UserService userService;

    /**
     * 获取临时公钥
     * 
     * @param mobile
     * @return
     */
    @GetMapping(value = "/tmpKey/{mobile}")
    @ACL(authControl = false)
    public Resp<String> getTmpPubkey(@PathVariable String mobile) {
        if(!MobileVerifyUtil.verifyMobile(mobile)){
            throw new BizException(RetCodeEnum.ILLEGAL_ARGUMENT);
        }
        RSAUtils.K k = RSAUtils.initKey();
        redisTool.hset(CodeKey.SECRET_KEY,"",mobile, JSON.toJSONString(k));
        return Resp.newSuccessResult(k.getPublicKey());
    }

    /**
     * 根据手机号码获取用户公钥
     * 
     * @param mobile
     * @return
     */
    @GetMapping(value = "/{mobile}", params = "mobile")
    @ACL(authControl = false)
    public Resp<String> getUserPubkeyByMobile(@PathVariable String mobile) {
        if (!MobileVerifyUtil.verifyMobile(mobile)) {
            throw new BizException(RetCodeEnum.ILLEGAL_ARGUMENT);
        }

        // TODO

        return null;
    }

    /**
     * 根据uid获取用户公钥
     *
     * @param uid
     * @return
     */
    @GetMapping(value = "/{uid}", params = "uid")
    @ACL
    public Resp<String> getUserPubkeyByUid(@PathVariable String uid) {

        // TODO 
        return null;
    }

    /**
     * 获取手机验证码
     * @return
     */
    @GetMapping(value = "/verifyCode/{mobile}")
    @ACL(authControl = false)
    public Resp getVerify(@PathVariable String mobile){
        if(!MobileVerifyUtil.verifyMobile(mobile)){
            throw new BizException(RetCodeEnum.ILLEGAL_ARGUMENT);
        }
        try {
            String verifyCode = MsgTool.sendVerifyCode(mobile);
            redisTool.hset(CodeKey.VERIFY_CODE, VerifyCodeEnum.REGISTER,mobile,verifyCode);
        } catch (Exception e) {
            throw new BizException("短信发送异常",e);
        }
        return Resp.newSuccessResult();
    }
}
