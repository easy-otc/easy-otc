package com.easytech.otc.mapper.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "ad")
public class Ad {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 广告类型
     */
    @Column(name = "ad_type")
    private Byte adType;

    /**
     * 币种
     */
    @Column(name = "coin_type")
    private Byte coinType;

    /**
     * 币数量
     */
    private BigDecimal amount;

    /**
     * 法币单价，单位：元
     */
    @Column(name = "legal_price")
    private BigDecimal legalPrice;

    /**
     * 交易条款
     */
    @Column(name = "Transaction_term")
    private String transactionTerm;

    /**
     * 是否仅限于已实名认证用户,0-否，1-是
     */
    @Column(name = "only_real_name_authed_user")
    private Byte onlyRealNameAuthedUser;

    /**
     * 广告状态,0-上单，1-撤单，2-删除
     */
    private Byte status;

    /**
     * 记录创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取广告类型
     *
     * @return ad_type - 广告类型
     */
    public Byte getAdType() {
        return adType;
    }

    /**
     * 设置广告类型
     *
     * @param adType 广告类型
     */
    public void setAdType(Byte adType) {
        this.adType = adType;
    }

    /**
     * 获取币种
     *
     * @return coin_type - 币种
     */
    public Byte getCoinType() {
        return coinType;
    }

    /**
     * 设置币种
     *
     * @param coinType 币种
     */
    public void setCoinType(Byte coinType) {
        this.coinType = coinType;
    }

    /**
     * 获取币数量
     *
     * @return amount - 币数量
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置币数量
     *
     * @param amount 币数量
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取法币单价，单位：元
     *
     * @return legal_price - 法币单价，单位：元
     */
    public BigDecimal getLegalPrice() {
        return legalPrice;
    }

    /**
     * 设置法币单价，单位：元
     *
     * @param legalPrice 法币单价，单位：元
     */
    public void setLegalPrice(BigDecimal legalPrice) {
        this.legalPrice = legalPrice;
    }

    /**
     * 获取交易条款
     *
     * @return Transaction_term - 交易条款
     */
    public String getTransactionTerm() {
        return transactionTerm;
    }

    /**
     * 设置交易条款
     *
     * @param transactionTerm 交易条款
     */
    public void setTransactionTerm(String transactionTerm) {
        this.transactionTerm = transactionTerm == null ? null : transactionTerm.trim();
    }

    /**
     * 获取是否仅限于已实名认证用户,0-否，1-是
     *
     * @return only_real_name_authed_user - 是否仅限于已实名认证用户,0-否，1-是
     */
    public Byte getOnlyRealNameAuthedUser() {
        return onlyRealNameAuthedUser;
    }

    /**
     * 设置是否仅限于已实名认证用户,0-否，1-是
     *
     * @param onlyRealNameAuthedUser 是否仅限于已实名认证用户,0-否，1-是
     */
    public void setOnlyRealNameAuthedUser(Byte onlyRealNameAuthedUser) {
        this.onlyRealNameAuthedUser = onlyRealNameAuthedUser;
    }

    /**
     * 获取广告状态,0-上单，1-撤单，2-删除
     *
     * @return status - 广告状态,0-上单，1-撤单，2-删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置广告状态,0-上单，1-撤单，2-删除
     *
     * @param status 广告状态,0-上单，1-撤单，2-删除
     */
    public void setStatus(Byte status) {
        this.status = status;
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
}