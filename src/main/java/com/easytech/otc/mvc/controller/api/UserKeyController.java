package com.easytech.otc.mvc.controller.api;

import com.easytech.otc.common.ImgCodeUtil;
import com.easytech.otc.common.MailUtil;
import com.easytech.otc.common.crypt.CryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 20:36
 */
@RestController
@RequestMapping(WebConst.API_V1_PREFIX + "user/key")
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
    @GetMapping(value = "/verifyCode/{mobile}/{imgCode}")
    @ACL(authControl = false)
    public Resp getVerify(@PathVariable String mobile,@PathVariable String imgCode){
        Resp resp = new Resp();
        if(!MobileVerifyUtil.verifyMobile(mobile)){
            throw new BizException(RetCodeEnum.ILLEGAL_ARGUMENT);
        }
        String redisImgCode = redisTool.hget(CodeKey.IMAGE_CODE, "", mobile);
        if(redisImgCode==null){
            resp.setFail(RetCodeEnum.ILLEGAL_ARGUMENT);
            return resp;
        }
        if(!redisImgCode.equals(imgCode)){
            resp.setFail(RetCodeEnum.ILLEGAL_ARGUMENT);
            return resp;
        }
        try {
            String verifyCode = MsgTool.sendVerifyCode(mobile);
            redisTool.hset(CodeKey.VERIFY_CODE, VerifyCodeEnum.REGISTER,mobile,verifyCode);
        } catch (Exception e) {
            throw new BizException("短信发送异常",e);
        }
        return Resp.newSuccessResult();
    }
    @RequestMapping(value = "test")
    @ACL(authControl = false)
    public Resp test(){
        return Resp.newSuccessResult();
    }

    /**
     * 获取身份认证 验证邮件
     * @return
     */

    @PostMapping(value = "/verifyEmail")
    @ACL
    public Resp getVerifyEmail(HttpServletRequest request,@RequestHeader(name = "identity") String uid, @RequestParam String email){
        Resp resp = new Resp();
        if(!MailUtil.isEmail(email)){
            resp.setFail(RetCodeEnum.ILLEGAL_ARGUMENT);
            return resp;
        }
        long curretTime = System.currentTimeMillis();
        String code = String.valueOf(curretTime)+uid;
        redisTool.set(CodeKey.CONFIRM_EMAIL_CODE,uid,code);

        String url = request.getScheme() +"://" + request.getServerName()+ ":" +request.getServerPort()
                +WebConst.API_V1_PREFIX+"user/key/confirm/"+uid+"/"+CryptUtil.md5(code);
        MailUtil.sendMail(email,"邮箱激活","success");
        System.out.println(url);
        return Resp.newSuccessResult();
    }

    /**
     * 验证邮箱信息
     * @return
     */
    @GetMapping(value = "/confirm/{uid}/{code}")
    @ACL(authControl = false)
    public Resp confirmEmail(@PathVariable String uid,@PathVariable String code){
        Resp resp = new Resp();
        String reidsCode = redisTool.get(CodeKey.CONFIRM_EMAIL_CODE, uid);
        if(reidsCode==null){
            resp.setFail(RetCodeEnum.ILLEGAL_ARGUMENT);
            return resp;
        }
        if(!Objects.equals(CryptUtil.md5(reidsCode),code)){
            resp.setFail(RetCodeEnum.ILLEGAL_ARGUMENT);
            return resp;
        }
        if(userService.updateEmailStatus(Integer.valueOf(uid))<1){
            resp.setFail(RetCodeEnum.INTERNAL_ERROR);
            return resp;
        }
        redisTool.del(CodeKey.CONFIRM_EMAIL_CODE, uid);
        return Resp.newSuccessResult();
    }
    @GetMapping(value = "/{mobile}", params = "imgCode")
    @ACL(authControl = false)
    public void getImgeCode(HttpServletResponse response,@PathVariable String mobile){

        if(!MobileVerifyUtil.verifyMobile(mobile)){
          return;
        }
        String code = ImgCodeUtil.genCode();
        redisTool.hset(CodeKey.IMAGE_CODE,"",mobile,code);
        BufferedImage imgeCode = ImgCodeUtil.getImgeCode(code, 100, 50, 6);
        try {
            ImageIO.write(imgeCode, "JPEG", response.getOutputStream());
        }catch (Exception e){
            throw  new BizException(RetCodeEnum.INTERNAL_ERROR);
        }
    }
}
