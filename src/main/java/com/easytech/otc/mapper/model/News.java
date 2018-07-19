package com.easytech.otc.mapper.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "news")
public class News {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 讯息类型，0-公告，1-新闻
     */
    @Column(name = "news_type")
    private Integer newsType;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 讯息状态,0-发布，1-撤销
     */
    private Integer status;

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
     * 内容。以HTML的形式存储，前端拿到后直接渲染
     */
    private String content;

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
     * 获取讯息类型，0-公告，1-新闻
     *
     * @return news_type - 讯息类型，0-公告，1-新闻
     */
    public Integer getNewsType() {
        return newsType;
    }

    /**
     * 设置讯息类型，0-公告，1-新闻
     *
     * @param newsType 讯息类型，0-公告，1-新闻
     */
    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * 获取讯息状态,0-发布，1-撤销
     *
     * @return status - 讯息状态,0-发布，1-撤销
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置讯息状态,0-发布，1-撤销
     *
     * @param status 讯息状态,0-发布，1-撤销
     */
    public void setStatus(Integer status) {
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
     * 获取内容。以HTML的形式存储，前端拿到后直接渲染
     *
     * @return content - 内容。以HTML的形式存储，前端拿到后直接渲染
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容。以HTML的形式存储，前端拿到后直接渲染
     *
     * @param content 内容。以HTML的形式存储，前端拿到后直接渲染
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}