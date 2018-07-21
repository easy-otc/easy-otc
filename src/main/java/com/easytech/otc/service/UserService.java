package com.easytech.otc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytech.otc.mapper.UserMapper;
import com.easytech.otc.mapper.model.User;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/20 22:46
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

    public boolean mobileExists(String mobile) {
        return false;
    }

    public boolean nameExists(String nameExists) {
        return false;
    }
}
