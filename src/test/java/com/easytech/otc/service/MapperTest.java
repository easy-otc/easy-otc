package com.easytech.otc.service;

import com.easytech.otc.mapper.UserMapper;
import com.easytech.otc.mapper.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

public class MapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testSelect() {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id", 1);
        User user = userMapper.selectOneByExample(example);
    }

    @Test
    public void testInsert() {

        User user = new User();
        int i = userMapper.insert(user);

        System.out.println(i);
    }
}
