
package com.easytech.otc.mvc.protocol;

import com.easytech.otc.cache.UserKey;
import com.easytech.otc.manager.redis.support.RedisTool;
import com.easytech.otc.mapper.model.User;
import com.easytech.otc.mvc.vo.LoginReturnVO;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

/**
 * 用户登陆信息存储
 */
@Slf4j
@Component
public class AuthedInfoRepository {
    @Autowired
    private RedisTool redisTool;

    public AuthedInfo getAuthedInfo(String token) {
        Map<String, String> stringStringMap = redisTool.hgetAll(UserKey.USER_INFO, token);

        return null;
    }

    public void saveAuthedInfo(AuthedInfo authedInfo) {
        Map<String,String> map = new HashMap<>();
        map.put("userName",authedInfo.getUserName());
        map.put("uid",String.valueOf(authedInfo.getUid()));
        map.put("mobile",authedInfo.getMobile());
        map.put("invitionCode",authedInfo.getInvitionCode());
        map.put("invitedBy",String.valueOf(authedInfo.getInvitedBy()));
        map.put("email",authedInfo.getEmail()==null?"":authedInfo.getEmail());
        redisTool.hmset(UserKey.USER_INFO, authedInfo.getToken(),map);
    }

    public LoginReturnVO saveLogin(User user){
        String token = createToken();
        AuthedInfo authedInfo = changeUserToAuthedInfo(user,token);
        LoginReturnVO loginReturnVO = changeUserToLoginReturnVO(user,token);
        saveAuthedInfo(authedInfo);
        return loginReturnVO;
    }

    private LoginReturnVO changeUserToLoginReturnVO(User user,String token){

        LoginReturnVO loginReturnVO = new LoginReturnVO();
        loginReturnVO.setUid(user.getId());
        loginReturnVO.setInvitionCode(user.getInvitionCode());
        loginReturnVO.setInvitedBy(user.getInvitedBy());
        loginReturnVO.setEmail(user.getEmail());
        loginReturnVO.setMobile(user.getMobile());
        loginReturnVO.setUserName(user.getName());
        loginReturnVO.setToken(token);
        return loginReturnVO;
    }

    private AuthedInfo changeUserToAuthedInfo(User user,String token){
        AuthedInfo authedInfo = new AuthedInfo();
        authedInfo.setUid(user.getId());
        authedInfo.setInvitionCode(user.getInvitionCode());
        authedInfo.setInvitedBy(user.getInvitedBy());
        authedInfo.setEmail(user.getEmail());
        authedInfo.setMobile(user.getMobile());
        authedInfo.setUserName(user.getName());
        authedInfo.setToken(token);
        return authedInfo;
    }

    public String createToken(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-","");
    }
}
