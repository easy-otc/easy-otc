package com.easytech.otc.mapper.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "legal_flow")
public class LegalFlow {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer    id;

    /**
     * 用户id
     */
    private Integer    uid;

    /**
     * 流水类型，0-充，1-提，2-手续费，3-手续费返还，充提属于主动流水，手续费、手续费返还属于自动流水
     */
    @Column(name = "op_type")
    private Integer    opType;

    /**
     * 发生金额，单位：元
     */
    private BigDecimal amount;

    /**
     * 发生之前金额
     */
    @Column(name = "before_amount")
    private BigDecimal beforeAmount;

    /**
     * 发生之后金额
     */
    @Column(name = "after_amount")
    private BigDecimal afterAmount;

    /**
     * 记录创建时间
     */
    @Column(name = "create_time")
    private Date       createTime;

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
     * 获取流水类型，0-充，1-提，2-手续费，3-手续费返还，充提属于主动流水，手续费、手续费返还属于自动流水
     *
     * @return op_type - 流水类型，0-充，1-提，2-手续费，3-手续费返还，充提属于主动流水，手续费、手续费返还属于自动流水
     */
    public Integer getOpType() {
        return opType;
    }

    /**
     * 设置流水类型，0-充，1-提，2-手续费，3-手续费返还，充提属于主动流水，手续费、手续费返还属于自动流水
     *
     * @param opType 流水类型，0-充，1-提，2-手续费，3-手续费返还，充提属于主动流水，手续费、手续费返还属于自动流水
     */
    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    /**
     * 获取发生金额，单位：元
     *
     * @return amount - 发生金额，单位：元
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置发生金额，单位：元
     *
     * @param amount 发生金额，单位：元
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取发生之前金额
     *
     * @return before_amount - 发生之前金额
     */
    public BigDecimal getBeforeAmount() {
        return beforeAmount;
    }

    /**
     * 设置发生之前金额
     *
     * @param beforeAmount 发生之前金额
     */
    public void setBeforeAmount(BigDecimal beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    /**
     * 获取发生之后金额
     *
     * @return after_amount - 发生之后金额
     */
    public BigDecimal getAfterAmount() {
        return afterAmount;
    }

    /**
     * 设置发生之后金额
     *
     * @param afterAmount 发生之后金额
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