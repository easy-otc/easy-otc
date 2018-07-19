package com.easytech.otc.mapper.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "coin")
public class Coin {
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
     * 币种
     */
    @Column(name = "coin_type")
    private Byte coinType;

    /**
     * 私钥
     */
    @Column(name = "private_key")
    private String privateKey;

    /**
     * 公钥
     */
    @Column(name = "public_key")
    private String publicKey;

    /**
     * 地址
     */
    private String address;

    /**
     * 币数量
     */
    private BigDecimal amount;

    /**
     * 锁定币数量，可用token数量 = amount - lock_amount
     */
    @Column(name = "lock_amount")
    private BigDecimal lockAmount;

    /**
     * 记录创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取私钥
     *
     * @return private_key - 私钥
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * 设置私钥
     *
     * @param privateKey 私钥
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey == null ? null : privateKey.trim();
    }

    /**
     * 获取公钥
     *
     * @return public_key - 公钥
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * 设置公钥
     *
     * @param publicKey 公钥
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey == null ? null : publicKey.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
     * 获取锁定币数量，可用token数量 = amount - lock_amount
     *
     * @return lock_amount - 锁定币数量，可用token数量 = amount - lock_amount
     */
    public BigDecimal getLockAmount() {
        return lockAmount;
    }

    /**
     * 设置锁定币数量，可用token数量 = amount - lock_amount
     *
     * @param lockAmount 锁定币数量，可用token数量 = amount - lock_amount
     */
    public void setLockAmount(BigDecimal lockAmount) {
        this.lockAmount = lockAmount;
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