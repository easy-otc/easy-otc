
package com.easytech.otc.mvc.protocol;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easytech.otc.cache.UserKey;
import com.easytech.otc.manager.redis.support.RedisTool;
import com.easytech.otc.mapper.model.User;
import com.easytech.otc.mvc.vo.LoginReturnVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户登陆信息存储
 */
@Slf4j
@Component
public class AuthedInfoRepository {
    @Autowired
    private RedisTool redisTool;

    public AuthedInfo getAuthedInfo(String token) {
        Map<String, String> map = redisTool.hgetAll(UserKey.USER_INFO, token);

        if(map.size()==0){
            return null;
        }
        AuthedInfo authedInfo = new AuthedInfo();
        authedInfo.setToken(token);
        authedInfo.setUserName(map.get("userName"));
        authedInfo.setUid(Integer.valueOf(map.get("uid")));
        authedInfo.setMobile(map.get("mobile"));
        authedInfo.setInvitionCode(map.get("invitionCode"));
        authedInfo.setInvitedBy(Integer.valueOf(map.get("invitedBy")));
        authedInfo.setEmail(map.get("email"));
        authedInfo.setCreateTime(Long.valueOf(map.get("createTime")));
        authedInfo.setTtl(redisTool.TTL(UserKey.USER_INFO, token));

        return authedInfo;
    }

    public void exprieAuthedInfo(AuthedInfo authedInfo){
        redisTool.expire(UserKey.USER_INFO,authedInfo.getToken(),UserKey.USER_INFO.expire());
    }

    public void saveAuthedInfo(AuthedInfo authedInfo) {
        Map<String,String> map = new HashMap<>();
        map.put("userName",authedInfo.getUserName());
        map.put("uid",String.valueOf(authedInfo.getUid()));
        map.put("mobile",authedInfo.getMobile());
        map.put("invitionCode",authedInfo.getInvitionCode());
        map.put("invitedBy",String.valueOf(authedInfo.getInvitedBy()));
        map.put("email",authedInfo.getEmail()==null?"":authedInfo.getEmail());
        map.put("createTime",String.valueOf(authedInfo.getCreateTime()));
        redisTool.hmset(UserKey.USER_INFO, authedInfo.getToken(),map);
    }

    public LoginReturnVO saveLogin(User user) {
        String token = createToken();
        AuthedInfo authedInfo = changeUserToAuthedInfo(user, token);
        LoginReturnVO loginReturnVO = changeUserToLoginReturnVO(user, token);
        saveAuthedInfo(authedInfo);
        return loginReturnVO;
    }

    private LoginReturnVO changeUserToLoginReturnVO(User user, String token) {

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

    private AuthedInfo changeUserToAuthedInfo(User user, String token) {
        AuthedInfo authedInfo = new AuthedInfo();
        authedInfo.setUid(user.getId());
        authedInfo.setInvitionCode(user.getInvitionCode());
        authedInfo.setInvitedBy(user.getInvitedBy());
        authedInfo.setEmail(user.getEmail());
        authedInfo.setMobile(user.getMobile());
        authedInfo.setUserName(user.getName());
        authedInfo.setToken(token);
        authedInfo.setCreateTime(System.currentTimeMillis());
        return authedInfo;
    }

    public String createToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
