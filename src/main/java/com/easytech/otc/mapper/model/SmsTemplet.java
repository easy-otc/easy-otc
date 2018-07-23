package com.easytech.otc.mapper.model;

import java.util.Date;

import javax.persistence.*;

import com.easytech.otc.common.mybatis.plugin.BaseModel;

@Table(name = "sms_templet")
public class SmsTemplet extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 短信模板id
     */
    @Column(name = "ali_templet_id")
    private String  aliTempletId;

    /**
     * 短信模板内容
     */
    private String  content;

    /**
     * 短信类型 1注册 2...
     */
    @Column(name = "sms_type")
    private Integer smsType;

    /**
     * 模板状态 1 可用 0不可用
     */
    private Integer status;

    @Column(name = "create_time")
    private Date    createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取短信模板id
     *
     * @return ali_templet_id - 短信模板id
     */
    public String getAliTempletId() {
        return aliTempletId;
    }

    /**
     * 设置短信模板id
     *
     * @param aliTempletId 短信模板id
     */
    public void setAliTempletId(String aliTempletId) {
        this.aliTempletId = aliTempletId == null ? null : aliTempletId.trim();
    }

    /**
     * 获取短信模板内容
     *
     * @return content - 短信模板内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置短信模板内容
     *
     * @param content 短信模板内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
     * 获取模板状态 1 可用 0不可用
     *
     * @return status - 模板状态 1 可用 0不可用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置模板状态 1 可用 0不可用
     *
     * @param status 模板状态 1 可用 0不可用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}