package com.easytech.otc.mvc.controller.api;

import com.easytech.otc.common.MobileVerifyUtil;
import com.easytech.otc.common.crypt.RSAUtils;
import com.easytech.otc.exception.BizException;
import com.easytech.otc.mvc.controller.Cache;
import com.easytech.otc.mvc.controller.WebConst;
import com.easytech.otc.mvc.protocol.ACL;
import com.easytech.otc.mvc.protocol.Resp;
import com.easytech.otc.mvc.protocol.RetCodeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 20:36
 */
@RestController
@RequestMapping(WebConst.API_V1_PREFIX + "/user/key")
public class UserController {

    private static final String MOBILE_PREFIX = "_MOBILE";
    private static final String UID_PREFIX    = "_UID";

    /**
     * 获取临时公钥
     * 
     * @param mobile
     * @return
     */
    @GetMapping(value = "/{mobile}/tmp")
    @ACL(authControl = false)
    public Resp<String> getTmpPubkey(@PathVariable String mobile) {

        RSAUtils.K k = RSAUtils.initKey();
        Cache.putTmpKey(MOBILE_PREFIX + mobile, k);

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
     * @param mobile
     * @return
     */
    @GetMapping(value = "/{uid}", params = "uid")
    @ACL
    public Resp<String> getUserPubkeyByUid(@PathVariable String uid) {

        // TODO 
        return null;
    }
}
