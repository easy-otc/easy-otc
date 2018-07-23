package com.easytech.otc.mapper.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import com.easytech.otc.common.mybatis.plugin.BaseModel;

@Table(name = "t_order")
public class Order extends BaseModel {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer    id;

    /**
     * 订单号
     */
    @Column(name = "order_id")
    private String     orderId;

    /**
     * 用户id
     */
    private Integer    uid;

    /**
     * 订单备注号
     */
    @Column(name = "memo_code")
    private String     memoCode;

    /**
     * 订单类型，0-买单，1-卖单
     */
    @Column(name = "order_type")
    private Integer    orderType;

    /**
     * 广告id
     */
    @Column(name = "ad_id")
    private Integer    adId;

    /**
     * 币种
     */
    @Column(name = "coin_type")
    private Integer    coinType;

    /**
     * 交易数量
     */
    private BigDecimal amount;

    /**
     * 法币单价，单位：元
     */
    @Column(name = "legal_price")
    private BigDecimal legalPrice;

    /**
     * 法币总价
     */
    @Column(name = "total_legal_price")
    private BigDecimal totalLegalPrice;

    /**
     * 支付时间
     */
    @Column(name = "payment_time")
    private Date       paymentTime;

    /**
     * 订单状态，0-已下单，1-已支付，2-已取消，3-已完成
     */
    @Column(name = "order_status")
    private Integer    orderStatus;

    /**
     * 放币交易的hash
     */
    @Column(name = "coin_release_tx_hash")
    private String     coinReleaseTxHash;

    /**
     * 放币状态，0-未放币，1-已放币，2-币交易接口调用失败，3-币交易接口已调用，4-币交易已提交到网络，5-币交易上链成功
     */
    @Column(name = "coin_release_status")
    private Integer    coinReleaseStatus;

    /**
     * 放币时间
     */
    @Column(name = "coin_release_time")
    private Date       coinReleaseTime;

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
     * 获取订单号
     *
     * @return order_id - 订单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单号
     *
     * @param orderId 订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
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
     * 获取订单备注号
     *
     * @return memo_code - 订单备注号
     */
    public String getMemoCode() {
        return memoCode;
    }

    /**
     * 设置订单备注号
     *
     * @param memoCode 订单备注号
     */
    public void setMemoCode(String memoCode) {
        this.memoCode = memoCode == null ? null : memoCode.trim();
    }

    /**
     * 获取订单类型，0-买单，1-卖单
     *
     * @return order_type - 订单类型，0-买单，1-卖单
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型，0-买单，1-卖单
     *
     * @param orderType 订单类型，0-买单，1-卖单
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取广告id
     *
     * @return ad_id - 广告id
     */
    public Integer getAdId() {
        return adId;
    }

    /**
     * 设置广告id
     *
     * @param adId 广告id
     */
    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    /**
     * 获取币种
     *
     * @return coin_type - 币种
     */
    public Integer getCoinType() {
        return coinType;
    }

    /**
     * 设置币种
     *
     * @param coinType 币种
     */
    public void setCoinType(Integer coinType) {
        this.coinType = coinType;
    }

    /**
     * 获取交易数量
     *
     * @return amount - 交易数量
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置交易数量
     *
     * @param amount 交易数量
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
     * 获取法币总价
     *
     * @return total_legal_price - 法币总价
     */
    public BigDecimal getTotalLegalPrice() {
        return totalLegalPrice;
    }

    /**
     * 设置法币总价
     *
     * @param totalLegalPrice 法币总价
     */
    public void setTotalLegalPrice(BigDecimal totalLegalPrice) {
        this.totalLegalPrice = totalLegalPrice;
    }

    /**
     * 获取支付时间
     *
     * @return payment_time - 支付时间
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 设置支付时间
     *
     * @param paymentTime 支付时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 获取订单状态，0-已下单，1-已支付，2-已取消，3-已完成
     *
     * @return order_status - 订单状态，0-已下单，1-已支付，2-已取消，3-已完成
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态，0-已下单，1-已支付，2-已取消，3-已完成
     *
     * @param orderStatus 订单状态，0-已下单，1-已支付，2-已取消，3-已完成
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取放币交易的hash
     *
     * @return coin_release_tx_hash - 放币交易的hash
     */
    public String getCoinReleaseTxHash() {
        return coinReleaseTxHash;
    }

    /**
     * 设置放币交易的hash
     *
     * @param coinReleaseTxHash 放币交易的hash
     */
    public void setCoinReleaseTxHash(String coinReleaseTxHash) {
        this.coinReleaseTxHash = coinReleaseTxHash == null ? null : coinReleaseTxHash.trim();
    }

    /**
     * 获取放币状态，0-未放币，1-已放币，2-币交易接口调用失败，3-币交易接口已调用，4-币交易已提交到网络，5-币交易上链成功
     *
     * @return coin_release_status - 放币状态，0-未放币，1-已放币，2-币交易接口调用失败，3-币交易接口已调用，4-币交易已提交到网络，5-币交易上链成功
     */
    public Integer getCoinReleaseStatus() {
        return coinReleaseStatus;
    }

    /**
     * 设置放币状态，0-未放币，1-已放币，2-币交易接口调用失败，3-币交易接口已调用，4-币交易已提交到网络，5-币交易上链成功
     *
     * @param coinReleaseStatus 放币状态，0-未放币，1-已放币，2-币交易接口调用失败，3-币交易接口已调用，4-币交易已提交到网络，5-币交易上链成功
     */
    public void setCoinReleaseStatus(Integer coinReleaseStatus) {
        this.coinReleaseStatus = coinReleaseStatus;
    }

    /**
     * 获取放币时间
     *
     * @return coin_release_time - 放币时间
     */
    public Date getCoinReleaseTime() {
        return coinReleaseTime;
    }

    /**
     * 设置放币时间
     *
     * @param coinReleaseTime 放币时间
     */
    public void setCoinReleaseTime(Date coinReleaseTime) {
        this.coinReleaseTime = coinReleaseTime;
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