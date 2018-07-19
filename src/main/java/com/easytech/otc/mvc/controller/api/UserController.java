package com.easytech.otc.mvc.controller.api;

import com.easytech.otc.common.crypt.RSAUtils;
import com.easytech.otc.mvc.controller.KeyValueConst;
import com.easytech.otc.mvc.controller.WebConst;
import com.easytech.otc.mvc.protocol.ACL;
import com.easytech.otc.mvc.protocol.Resp;
import com.easytech.otc.mvc.protocol.RetCodeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Key;
import java.util.Map;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 20:36
 */
@RestController
@RequestMapping(WebConst.API_V1_PREFIX + "/user")
public class UserController {
    /**
     * 获取公钥
     * @param session
     * @return
     */
    @GetMapping(value = "/getPublicKey")
    @ACL(authControl = false)
    public Resp<String> getSessionId(HttpSession session) {
        String privateKey;
        String publicKey = "";
        if (session.getAttribute(KeyValueConst.PUBLIC_KEY) != null) {
            return Resp.newSuccessResult((String) session.getAttribute(KeyValueConst.PUBLIC_KEY));
        }
        Resp<String> resp = new Resp<>();

        try {
            Map<String, Key> keyMap = RSAUtils.initKey();
            privateKey = RSAUtils.getPrivateKey(keyMap);
            publicKey = RSAUtils.getPublicKey(keyMap);
            session.setAttribute(KeyValueConst.PUBLIC_KEY, publicKey);
            session.setAttribute(KeyValueConst.PRIVATE_KEY, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setFail(RetCodeEnum.INTERNAL_ERROR);
        }
        return Resp.newSuccessResult(publicKey);
    }
}
