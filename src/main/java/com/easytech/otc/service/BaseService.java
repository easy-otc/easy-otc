package com.easytech.otc.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import com.easytech.otc.common.mybatis.plugin.BaseModel;
import com.easytech.otc.common.mybatis.plugin.OtcMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BaseService<T extends BaseModel> {

    @Autowired
    private OtcMapper<T> mapper;

    public PageInfo<T> queryPage(T t, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<T> list = this.mapper.select(t);
        return new PageInfo<>(list);
    }

    public int insert(T record) {
        return this.mapper.insert(record);
    }

    public int insertSelective(T record) {
        return this.mapper.insertSelective(record);
    }

    public List<T> getAll() {
        return this.mapper.selectAll();
    }

    public T getById(Integer key) {
        return this.mapper.selectByPrimaryKey(key);
    }

    public int getCount(T record) {
        return this.mapper.selectCount(record);
    }

    public List<T> select(T record) {
        return this.mapper.select(record);
    }

    public T selectOne(T record) {
        return this.mapper.selectOne(record);
    }

    public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
        return this.mapper.selectByRowBounds(record, rowBounds);
    }

    public int updateById(T record) {
        return this.mapper.updateByPrimaryKey(record);
    }

    public int updateByIdSelective(T record) {
        return this.mapper.updateByPrimaryKeySelective(record);
    }
}