package com.easytech.otc.mapper.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "work_order")
public class WorkOrder {
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
     * 工单类型，0-交易，1-账户，2-投诉，3-建议
     */
    @Column(name = "work_order_type")
    private Byte workOrderType;

    /**
     * 主题
     */
    private String theme;

    /**
     * 附件图片url
     */
    @Column(name = "attach_url")
    private String attachUrl;

    /**
     * 工单状态，0-提交，2-处理中，3-处理完成
     */
    private Byte status;

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
     * 内容
     */
    private String content;

    /**
     * 交流记录。以HTML的形式存储，前端拿到后直接渲染
     */
    @Column(name = "communicate_record")
    private String communicateRecord;

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
     * 获取工单类型，0-交易，1-账户，2-投诉，3-建议
     *
     * @return work_order_type - 工单类型，0-交易，1-账户，2-投诉，3-建议
     */
    public Byte getWorkOrderType() {
        return workOrderType;
    }

    /**
     * 设置工单类型，0-交易，1-账户，2-投诉，3-建议
     *
     * @param workOrderType 工单类型，0-交易，1-账户，2-投诉，3-建议
     */
    public void setWorkOrderType(Byte workOrderType) {
        this.workOrderType = workOrderType;
    }

    /**
     * 获取主题
     *
     * @return theme - 主题
     */
    public String getTheme() {
        return theme;
    }

    /**
     * 设置主题
     *
     * @param theme 主题
     */
    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    /**
     * 获取附件图片url
     *
     * @return attach_url - 附件图片url
     */
    public String getAttachUrl() {
        return attachUrl;
    }

    /**
     * 设置附件图片url
     *
     * @param attachUrl 附件图片url
     */
    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl == null ? null : attachUrl.trim();
    }

    /**
     * 获取工单状态，0-提交，2-处理中，3-处理完成
     *
     * @return status - 工单状态，0-提交，2-处理中，3-处理完成
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置工单状态，0-提交，2-处理中，3-处理完成
     *
     * @param status 工单状态，0-提交，2-处理中，3-处理完成
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

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取交流记录。以HTML的形式存储，前端拿到后直接渲染
     *
     * @return communicate_record - 交流记录。以HTML的形式存储，前端拿到后直接渲染
     */
    public String getCommunicateRecord() {
        return communicateRecord;
    }

    /**
     * 设置交流记录。以HTML的形式存储，前端拿到后直接渲染
     *
     * @param communicateRecord 交流记录。以HTML的形式存储，前端拿到后直接渲染
     */
    public void setCommunicateRecord(String communicateRecord) {
        this.communicateRecord = communicateRecord == null ? null : communicateRecord.trim();
    }
}