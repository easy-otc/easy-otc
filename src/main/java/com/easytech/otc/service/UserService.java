package com.easytech.otc.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.easytech.otc.cache.CodeKey;
import com.easytech.otc.common.InviteUtil;
import com.easytech.otc.common.MobileVerifyUtil;
import com.easytech.otc.common.PasswdUtil;
import com.easytech.otc.common.crypt.RSAUtils;
import com.easytech.otc.enums.VerifyCodeEnum;
import com.easytech.otc.enums.YesNoEnum;
import com.easytech.otc.exception.BizException;
import com.easytech.otc.manager.redis.support.RedisTool;
import com.easytech.otc.mapper.UserMapper;
import com.easytech.otc.mapper.model.User;
import com.easytech.otc.mvc.protocol.RetCodeEnum;
import com.easytech.otc.mvc.vo.LoginRequest;
import com.easytech.otc.mvc.vo.RegisterRequest;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/20 22:46
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper  userMapper;
    @Autowired
    private RedisTool   redisTool;

    @Autowired
    private CoinService coinService;

    public boolean nameExists(String name) {
        return getUserByName(name) != null;
    }

    public User getUserByName(String name) {
        User user = new User();
        user.setName(name);
        return userMapper.selectOne(user);
    }

    public boolean mobileExists(String mobile) {
        return getUserByMobile(mobile) != null;
    }

    public User getUserByMobile(String mobile) {
        User user = new User();
        user.setMobile(mobile);
        return this.selectOne(user);
    }

    public boolean idExists(Integer id) {
        return this.getById(id) != null;
    }

    public void checkLoginRequest(LoginRequest loginRequest) {
        Assert.hasLength(loginRequest.getMobile(), "手机号码不能为空");
        if (!MobileVerifyUtil.verifyMobile(loginRequest.getMobile())) {
            throw new IllegalArgumentException("手机号码输入有误");
        }
        Assert.hasLength(loginRequest.getPassword(), "密码不能为空");
    }

    public void checkRegisterRequest(RegisterRequest registerRequest) {

        Assert.hasLength(registerRequest.getUserName(), "用户名不能为空");
        if (registerRequest.getUserName().length() > 64) {
            throw new IllegalArgumentException("用户名太长");
        }
        Assert.hasLength(registerRequest.getMobile(), "手机号码不能为空");
        if (!MobileVerifyUtil.verifyMobile(registerRequest.getMobile())) {
            throw new IllegalArgumentException("手机号码输入有误");
        }

        Assert.hasLength(registerRequest.getCiphertext(), "密码不能为空");
        if (registerRequest.getCiphertext().length() < 8) {
            throw new IllegalArgumentException("密码太短");
        }
        if (registerRequest.getCiphertext().length() > 128) {
            throw new IllegalArgumentException("密码太长");
        }
        Assert.hasLength(registerRequest.getVerifyCode(), "验证码不能为空");
        if (registerRequest.getVerifyCode().length() != 6) {
            throw new IllegalArgumentException("手机验证码有误");
        }
        String verifyCode = redisTool.hget(CodeKey.VERIFY_CODE, VerifyCodeEnum.REGISTER, registerRequest.getMobile());
        if (!Objects.equals(verifyCode, registerRequest.getVerifyCode())) {
            throw new IllegalArgumentException("手机验证码有误");
        }
        Assert.hasLength(registerRequest.getInvitionCode(), "邀请码不能为空");
    }

    @Transactional
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.getUserName());
        user.setMobile(registerRequest.getMobile());
        String secretKeys = redisTool.hget(CodeKey.SECRET_KEY, "", registerRequest.getMobile());
        RSAUtils.K k = JSON.parseObject(secretKeys, RSAUtils.K.class);
        if (k == null) {
            throw new BizException(RetCodeEnum.INTERNAL_ERROR);
        }
        user.setLoginPasswordPrivateKey(k.getPrivateKey());
        user.setLoginPasswordPublicKey(k.getPublicKey());
        user.setLoginPassword(PasswdUtil.encode(registerRequest.getCiphertext()));
        RSAUtils.K fund = RSAUtils.initKey();

        user.setFundPasswordPrivateKey(fund.getPrivateKey());
        user.setFundPasswordPublicKey(fund.getPublicKey());
        user.setLegalAmount(BigDecimal.ZERO);
        user.setLockLegalAmount(BigDecimal.ZERO);
        user.setInvitionCode("0");
        Integer inviteBy = InviteUtil.getUidByCode(registerRequest.getInvitionCode());
        if (!idExists(inviteBy)) {
            throw new BizException(RetCodeEnum.INVITE_ERROR);
        }

        user.setInvitedBy(inviteBy);
        user.setTradeTimes(0);
        user.setTradeSuccessTimes(0);
        user.setIsRealNameAuthed(0);
        user.setIsEmailVerified(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);

        User u = new User();
        u.setId(user.getId());
        u.setInvitionCode(InviteUtil.getCodeByUid(user.getId()));
        this.updateById(u);

        // 生成用户所有币和代币地址
        coinService.genCoinAndTokenAccount(user.getId());
    }

    @Transactional
    public int updateEmailStatus(int uid) {
        User user = new User();
        user.setId(uid);
        user.setIsEmailVerified(YesNoEnum.YES.getCode());
        return this.updateById(user);
    }
}
