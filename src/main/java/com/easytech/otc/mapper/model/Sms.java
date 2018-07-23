package com.easytech.otc.mapper.model;

import java.util.Date;

import javax.persistence.*;

import com.easytech.otc.common.mybatis.plugin.BaseModel;

@Table(name = "sms")
public class Sms extends BaseModel {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 手机号码
     */
    private String  mobile;

    /**
     * 短信类型 1注册 2...
     */
    @Column(name = "sms_type")
    private Integer smsType;

    /**
     * 模板主键id
     */
    @Column(name = "sms_templet_id")
    private Integer smsTempletId;

    /**
     * 短信内容
     */
    private String  content;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date    createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
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
     * 获取短信类型 1注册 2...
     *
     * @return sms_type - 短信类型 1注册 2...
     */
    public Integer getSmsType() {
        return smsType;
    }

    /**
     * 设置短信类型 1注册 2...
     *
     * @param smsType 短信类型 1注册 2...
     */
    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    /**
     * 获取模板主键id
     *
     * @return sms_templet_id - 模板主键id
     */
    public Integer getSmsTempletId() {
        return smsTempletId;
    }

    /**
     * 设置模板主键id
     *
     * @param smsTempletId 模板主键id
     */
    public void setSmsTempletId(Integer smsTempletId) {
        this.smsTempletId = smsTempletId;
    }

    /**
     * 获取短信内容
     *
     * @return content - 短信内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置短信内容
     *
     * @param content 短信内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}