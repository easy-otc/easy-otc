package com.easytech.otc.mapper.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "coin_flow")
public class CoinFlow {
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
    private Boolean coinType;

    /**
     * 操作类型，0-充，1-提，2-买，3-卖，充提是主动流水，买卖是自动流水
     */
    @Column(name = "op_type")
    private Boolean opType;

    /**
     * 地址
     */
    private String address;

    /**
     * token数量
     */
    private BigDecimal amount;

    /**
     * 发生之前数量
     */
    @Column(name = "before_amount")
    private BigDecimal beforeAmount;

    /**
     * 交易消耗数量
     */
    @Column(name = "chain_cost_amount")
    private BigDecimal chainCostAmount;

    /**
     * 发生之后数量
     */
    @Column(name = "after_amount")
    private BigDecimal afterAmount;

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
     * 获取币种
     *
     * @return coin_type - 币种
     */
    public Boolean getCoinType() {
        return coinType;
    }

    /**
     * 设置币种
     *
     * @param coinType 币种
     */
    public void setCoinType(Boolean coinType) {
        this.coinType = coinType;
    }

    /**
     * 获取操作类型，0-充，1-提，2-买，3-卖，充提是主动流水，买卖是自动流水
     *
     * @return op_type - 操作类型，0-充，1-提，2-买，3-卖，充提是主动流水，买卖是自动流水
     */
    public Boolean getOpType() {
        return opType;
    }

    /**
     * 设置操作类型，0-充，1-提，2-买，3-卖，充提是主动流水，买卖是自动流水
     *
     * @param opType 操作类型，0-充，1-提，2-买，3-卖，充提是主动流水，买卖是自动流水
     */
    public void setOpType(Boolean opType) {
        this.opType = opType;
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
        this.address = address;
    }

    /**
     * 获取token数量
     *
     * @return amount - token数量
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置token数量
     *
     * @param amount token数量
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取发生之前数量
     *
     * @return before_amount - 发生之前数量
     */
    public BigDecimal getBeforeAmount() {
        return beforeAmount;
    }

    /**
     * 设置发生之前数量
     *
     * @param beforeAmount 发生之前数量
     */
    public void setBeforeAmount(BigDecimal beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    /**
     * 获取交易消耗数量
     *
     * @return chain_cost_amount - 交易消耗数量
     */
    public BigDecimal getChainCostAmount() {
        return chainCostAmount;
    }

    /**
     * 设置交易消耗数量
     *
     * @param chainCostAmount 交易消耗数量
     */
    public void setChainCostAmount(BigDecimal chainCostAmount) {
        this.chainCostAmount = chainCostAmount;
    }

    /**
     * 获取发生之后数量
     *
     * @return after_amount - 发生之后数量
     */
    public BigDecimal getAfterAmount() {
        return afterAmount;
    }

    /**
     * 设置发生之后数量
     *
     * @param afterAmount 发生之后数量
     */
    public void setAfterAmount(BigDecimal afterAmount) {
        this.afterAmount = afterAmount;
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