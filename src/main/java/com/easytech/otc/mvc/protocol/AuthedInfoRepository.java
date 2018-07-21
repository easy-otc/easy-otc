
package com.easytech.otc.mvc.protocol;

import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户登陆信息存储
 */
@Slf4j
public class AuthedInfoRepository {

    private AuthedInfoRepository() {
    }

    private static class Holder {
        private static final AuthedInfoRepository SINGLETON = new AuthedInfoRepository();
    }

    public static AuthedInfoRepository getInstance() {
        return Holder.SINGLETON;
    }

    // ----------------------------------------------------------------------------------------------------

    /**
     * <uid_token, AuthedInfo>
     */
    private ConcurrentMap<String, AuthedInfo> authedUsers = Maps.newConcurrentMap();

    public AuthedInfo getAuthedInfo(String uid, String token) {
        return authedUsers.get(uid + "_" + token);
    }

    public void saveAuthedInfo(AuthedInfo authedInfo) {
        String key = authedInfo.getUid() + "_" + authedInfo.getToken();
        authedUsers.put(key, authedInfo);
    }
}
