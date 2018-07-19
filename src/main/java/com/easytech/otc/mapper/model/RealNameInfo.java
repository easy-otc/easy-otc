package com.easytech.otc.mapper.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "real_name_info")
public class RealNameInfo {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 身份证号
     */
    @Column(name = "id_card_no")
    private String idCardNo;

    /**
     * 正面照片url
     */
    @Column(name = "id_card_front_pic_url")
    private String idCardFrontPicUrl;

    /**
     * 背面照片url
     */
    @Column(name = "id_card_back_pic_url")
    private String idCardBackPicUrl;

    /**
     * 手持照片url
     */
    @Column(name = "id_card_held_pic_url")
    private String idCardHeldPicUrl;

    /**
     * 实名认证状态，0-已提交，2-审核中，3-实名通过，4-实名认证失败
     */
    private Integer status;

    /**
     * 审核失败次数
     */
    @Column(name = "fail_times")
    private Integer failTimes;

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
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 获取身份证号
     *
     * @return id_card_no - 身份证号
     */
    public String getIdCardNo() {
        return idCardNo;
    }

    /**
     * 设置身份证号
     *
     * @param idCardNo 身份证号
     */
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    /**
     * 获取正面照片url
     *
     * @return id_card_front_pic_url - 正面照片url
     */
    public String getIdCardFrontPicUrl() {
        return idCardFrontPicUrl;
    }

    /**
     * 设置正面照片url
     *
     * @param idCardFrontPicUrl 正面照片url
     */
    public void setIdCardFrontPicUrl(String idCardFrontPicUrl) {
        this.idCardFrontPicUrl = idCardFrontPicUrl == null ? null : idCardFrontPicUrl.trim();
    }

    /**
     * 获取背面照片url
     *
     * @return id_card_back_pic_url - 背面照片url
     */
    public String getIdCardBackPicUrl() {
        return idCardBackPicUrl;
    }

    /**
     * 设置背面照片url
     *
     * @param idCardBackPicUrl 背面照片url
     */
    public void setIdCardBackPicUrl(String idCardBackPicUrl) {
        this.idCardBackPicUrl = idCardBackPicUrl == null ? null : idCardBackPicUrl.trim();
    }

    /**
     * 获取手持照片url
     *
     * @return id_card_held_pic_url - 手持照片url
     */
    public String getIdCardHeldPicUrl() {
        return idCardHeldPicUrl;
    }

    /**
     * 设置手持照片url
     *
     * @param idCardHeldPicUrl 手持照片url
     */
    public void setIdCardHeldPicUrl(String idCardHeldPicUrl) {
        this.idCardHeldPicUrl = idCardHeldPicUrl == null ? null : idCardHeldPicUrl.trim();
    }

    /**
     * 获取实名认证状态，0-已提交，2-审核中，3-实名通过，4-实名认证失败
     *
     * @return status - 实名认证状态，0-已提交，2-审核中，3-实名通过，4-实名认证失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置实名认证状态，0-已提交，2-审核中，3-实名通过，4-实名认证失败
     *
     * @param status 实名认证状态，0-已提交，2-审核中，3-实名通过，4-实名认证失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取审核失败次数
     *
     * @return fail_times - 审核失败次数
     */
    public Integer getFailTimes() {
        return failTimes;
    }

    /**
     * 设置审核失败次数
     *
     * @param failTimes 审核失败次数
     */
    public void setFailTimes(Integer failTimes) {
        this.failTimes = failTimes;
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