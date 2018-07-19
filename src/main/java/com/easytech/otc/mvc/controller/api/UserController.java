package com.easytech.otc.mvc.controller.api;

import com.easytech.otc.common.crypt.RSAUtils;
import com.easytech.otc.mvc.controller.WebConst;
import com.easytech.otc.mvc.protocol.ACL;
import com.easytech.otc.mvc.protocol.Resp;
import com.easytech.otc.mvc.protocol.RetCodeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Map;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 20:36
 */
@RestController
@RequestMapping(WebConst.API_V1_PREFIX + "/user")
public class UserController  {
    /**
     * 获取公钥
     * @param request
     * @return
     */
    @GetMapping(value = "/getPublicKey")
    @ACL(authControl = false)
    public Resp<String> getSessionId(HttpServletRequest request){
        Resp<String> resp= new Resp<>();
        Map<String, Key> keyMap =null;
        String privateKey = "";
        String publicKey = "";
        try {
            keyMap = RSAUtils.initKey();
            privateKey= RSAUtils.getPrivateKey(keyMap);
            publicKey = RSAUtils.getPublicKey(keyMap);
            request.getSession().setAttribute("publickey",publicKey);
            request.getSession().setAttribute("privateKey",privateKey);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setFail(RetCodeEnum.INTERNAL_ERROR);
        }
        return Resp.newSuccessResult(publicKey);
    }
}
