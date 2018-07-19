//package com.easytech.otc.service;
//
//import com.alibaba.fastjson.JSON;
//import com.easytech.otc.mapper.UserMapper;
//import com.easytech.otc.mapper.model.User;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.math.BigDecimal;
//
//public class MapperTest extends BaseTest {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Test
//    public void testSelect() {
//        User user = new User();
//        user.setId(1);
//        User u = userMapper.selectOne(user);
//        System.out.println(JSON.toJSONString(u));
//    }
//
//    @Test
//    public void testInsert() {
//
//        User user = new User();
//        user.setName("name");
//        user.setEmail("737093270@qq.com");
//        user.setLoginPassword("setLoginPassword");
//        user.setLoginPasswordPrivateKey("setLoginPasswordPrivateKey");
//        user.setLoginPasswordPublicKey("setLoginPasswordPublicKey");
//
//        user.setFundPassword("setFundPassword");
//        user.setFundPasswordPrivateKey("setFundPasswordPrivateKey");
//        user.setFundPasswordPublicKey("setFundPasswordPublicKey");
//
//        user.setLegalAmount(BigDecimal.ONE);
//        user.setInvitionCode("setInvit");
//        user.setTradeTimes(13);
//        user.setTradeSuccessTimes(11);
//
//        user.setIsRealNameAuthed();
//        user.setIsEmailVerified(false);
//        user.setIsMobileVerified(true);
//
//        int i = userMapper.insert(user);
//
//        System.out.println(i);
//    }
//}
