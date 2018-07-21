package com.easytech.otc.mapper.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user")
public class User {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer    id;

    /**
     * 用户名
     */
    @Column(name = "NAME")
    private String     name;

    /**
     * 邮箱
     */
    private String     email;

    /**
     * 手机号码
     */
    private String     mobile;

    /**
     * 登录密码
     */
    @Column(name = "login_password")
    private String     loginPassword;

    /**
     * 登录密码私钥
     */
    @Column(name = "login_password_private_key")
    private String     loginPasswordPrivateKey;

    /**
     * 登录密码公钥
     */
    @Column(name = "login_password_public_key")
    private String     loginPasswordPublicKey;

    /**
     * 资金密码
     */
    @Column(name = "fund_password")
    private String     fundPassword;

    /**
     * 资金密码私钥
     */
    @Column(name = "fund_password_private_key")
    private String     fundPasswordPrivateKey;

    /**
     * 资金密码公钥
     */
    @Column(name = "fund_password_public_key")
    private String     fundPasswordPublicKey;

    /**
     * 默认法币账号
     */
    @Column(name = "default_legal_account")
    private Integer    defaultLegalAccount;

    /**
     * 法币余额
     */
    @Column(name = "legal_amount")
    private BigDecimal legalAmount;

    /**
     * 锁定法币
     */
    @Column(name = "lock_legal_amount")
    private BigDecimal lockLegalAmount;

    /**
     * 邀请码
     */
    @Column(name = "invition_code")
    private String     invitionCode;

    /**
     * 邀请者
     */
    @Column(name = "invited_by")
    private Integer    invitedBy;

    /**
     * 交易次数
     */
    @Column(name = "trade_times")
    private Integer    tradeTimes;

    /**
     * 成功交易次数
     */
    @Column(name = "trade_success_times")
    private Integer    tradeSuccessTimes;

    /**
     * 好评率
     */
    @Column(name = "praise_rate")
    private BigDecimal praiseRate;

    /**
     * 最近10次登录ip，以竖线分隔
     */
    @Column(name = "login_ip")
    private String     loginIp;

    /**
     * 最近一次登录时间
     */
    @Column(name = "last_login_time")
    private Date       lastLoginTime;

    /**
     * 是否实名认证,0-否,1-是
     */
    @Column(name = "is_real_name_authed")
    private Integer    isRealNameAuthed;



    /**
     * 邮箱是否验证,0-否,1-是
     */
    @Column(name = "is_email_verified")
    private Integer    isEmailVerified;

    /**
     * 记录创建时间
     */
    @Column(name = "create_time")
    private Date       createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date       updateTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return NAME - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取登录密码
     *
     * @return login_password - 登录密码
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * 设置登录密码
     *
     * @param loginPassword 登录密码
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    /**
     * 获取登录密码私钥
     *
     * @return login_password_private_key - 登录密码私钥
     */
    public String getLoginPasswordPrivateKey() {
        return loginPasswordPrivateKey;
    }

    /**
     * 设置登录密码私钥
     *
     * @param loginPasswordPrivateKey 登录密码私钥
     */
    public void setLoginPasswordPrivateKey(String loginPasswordPrivateKey) {
        this.loginPasswordPrivateKey = loginPasswordPrivateKey == null ? null : loginPasswordPrivateKey.trim();
    }

    /**
     * 获取登录密码公钥
     *
     * @return login_password_public_key - 登录密码公钥
     */
    public String getLoginPasswordPublicKey() {
        return loginPasswordPublicKey;
    }

    /**
     * 设置登录密码公钥
     *
     * @param loginPasswordPublicKey 登录密码公钥
     */
    public void setLoginPasswordPublicKey(String loginPasswordPublicKey) {
        this.loginPasswordPublicKey = loginPasswordPublicKey == null ? null : loginPasswordPublicKey.trim();
    }

    /**
     * 获取资金密码
     *
     * @return fund_password - 资金密码
     */
    public String getFundPassword() {
        return fundPassword;
    }

    /**
     * 设置资金密码
     *
     * @param fundPassword 资金密码
     */
    public void setFundPassword(String fundPassword) {
        this.fundPassword = fundPassword == null ? null : fundPassword.trim();
    }

    /**
     * 获取资金密码私钥
     *
     * @return fund_password_private_key - 资金密码私钥
     */
    public String getFundPasswordPrivateKey() {
        return fundPasswordPrivateKey;
    }

    /**
     * 设置资金密码私钥
     *
     * @param fundPasswordPrivateKey 资金密码私钥
     */
    public void setFundPasswordPrivateKey(String fundPasswordPrivateKey) {
        this.fundPasswordPrivateKey = fundPasswordPrivateKey == null ? null : fundPasswordPrivateKey.trim();
    }

    /**
     * 获取资金密码公钥
     *
     * @return fund_password_public_key - 资金密码公钥
     */
    public String getFundPasswordPublicKey() {
        return fundPasswordPublicKey;
    }

    /**
     * 设置资金密码公钥
     *
     * @param fundPasswordPublicKey 资金密码公钥
     */
    public void setFundPasswordPublicKey(String fundPasswordPublicKey) {
        this.fundPasswordPublicKey = fundPasswordPublicKey == null ? null : fundPasswordPublicKey.trim();
    }

    /**
     * 获取默认法币账号
     *
     * @return default_legal_account - 默认法币账号
     */
    public Integer getDefaultLegalAccount() {
        return defaultLegalAccount;
    }

    /**
     * 设置默认法币账号
     *
     * @param defaultLegalAccount 默认法币账号
     */
    public void setDefaultLegalAccount(Integer defaultLegalAccount) {
        this.defaultLegalAccount = defaultLegalAccount;
    }

    /**
     * 获取法币余额
     *
     * @return legal_amount - 法币余额
     */
    public BigDecimal getLegalAmount() {
        return legalAmount;
    }

    /**
     * 设置法币余额
     *
     * @param legalAmount 法币余额
     */
    public void setLegalAmount(BigDecimal legalAmount) {
        this.legalAmount = legalAmount;
    }

    /**
     * 获取锁定法币
     *
     * @return lock_legal_amount - 锁定法币
     */
    public BigDecimal getLockLegalAmount() {
        return lockLegalAmount;
    }

    /**
     * 设置锁定法币
     *
     * @param lockLegalAmount 锁定法币
     */
    public void setLockLegalAmount(BigDecimal lockLegalAmount) {
        this.lockLegalAmount = lockLegalAmount;
    }

    /**
     * 获取邀请码
     *
     * @return invition_code - 邀请码
     */
    public String getInvitionCode() {
        return invitionCode;
    }

    /**
     * 设置邀请码
     *
     * @param invitionCode 邀请码
     */
    public void setInvitionCode(String invitionCode) {
        this.invitionCode = invitionCode == null ? null : invitionCode.trim();
    }

    /**
     * 获取邀请者
     *
     * @return invited_by - 邀请者
     */
    public Integer getInvitedBy() {
        return invitedBy;
    }

    /**
     * 设置邀请者
     *
     * @param invitedBy 邀请者
     */
    public void setInvitedBy(Integer invitedBy) {
        this.invitedBy = invitedBy;
    }

    /**
     * 获取交易次数
     *
     * @return trade_times - 交易次数
     */
    public Integer getTradeTimes() {
        return tradeTimes;
    }

    /**
     * 设置交易次数
     *
     * @param tradeTimes 交易次数
     */
    public void setTradeTimes(Integer tradeTimes) {
        this.tradeTimes = tradeTimes;
    }

    /**
     * 获取成功交易次数
     *
     * @return trade_success_times - 成功交易次数
     */
    public Integer getTradeSuccessTimes() {
        return tradeSuccessTimes;
    }

    /**
     * 设置成功交易次数
     *
     * @param tradeSuccessTimes 成功交易次数
     */
    public void setTradeSuccessTimes(Integer tradeSuccessTimes) {
        this.tradeSuccessTimes = tradeSuccessTimes;
    }

    /**
     * 获取好评率
     *
     * @return praise_rate - 好评率
     */
    public BigDecimal getPraiseRate() {
        return praiseRate;
    }

    /**
     * 设置好评率
     *
     * @param praiseRate 好评率
     */
    public void setPraiseRate(BigDecimal praiseRate) {
        this.praiseRate = praiseRate;
    }

    /**
     * 获取最近10次登录ip，以竖线分隔
     *
     * @return login_ip - 最近10次登录ip，以竖线分隔
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置最近10次登录ip，以竖线分隔
     *
     * @param loginIp 最近10次登录ip，以竖线分隔
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    /**
     * 获取最近一次登录时间
     *
     * @return last_login_time - 最近一次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最近一次登录时间
     *
     * @param lastLoginTime 最近一次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取是否实名认证,0-否,1-是
     *
     * @return is_real_name_authed - 是否实名认证,0-否,1-是
     */
    public Integer getIsRealNameAuthed() {
        return isRealNameAuthed;
    }

    /**
     * 设置是否实名认证,0-否,1-是
     *
     * @param isRealNameAuthed 是否实名认证,0-否,1-是
     */
    public void setIsRealNameAuthed(Integer isRealNameAuthed) {
        this.isRealNameAuthed = isRealNameAuthed;
    }


    /**
     * 获取邮箱是否验证,0-否,1-是
     *
     * @return is_email_verified - 邮箱是否验证,0-否,1-是
     */
    public Integer getIsEmailVerified() {
        return isEmailVerified;
    }

    /**
     * 设置邮箱是否验证,0-否,1-是
     *
     * @param isEmailVerified 邮箱是否验证,0-否,1-是
     */
    public void setIsEmailVerified(Integer isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    /**
     * 获取记录创建时间
     *
     * @return create_time - 记录创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置记录创建时间
     *
     * @param createTime 记录创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}