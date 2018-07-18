package com.easytech.otc.service;

import com.easytech.common.mybatis.plugin.OtcMapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public class DemoMapper implements OtcMapper<Object> {
    @Override
    public int deleteByPrimaryKey(Object key) {
        return 0;
    }

    @Override
    public int delete(Object record) {
        return 0;
    }

    @Override
    public int insert(Object record) {
        return 0;
    }

    @Override
    public int insertSelective(Object record) {
        return 0;
    }

    @Override
    public boolean existsWithPrimaryKey(Object key) {
        return false;
    }

    @Override
    public List selectAll() {
        return null;
    }

    @Override
    public Object selectByPrimaryKey(Object key) {
        return null;
    }

    @Override
    public int selectCount(Object record) {
        return 0;
    }

    @Override
    public List select(Object record) {
        return null;
    }

    @Override
    public Object selectOne(Object record) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Object record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Object record) {
        return 0;
    }

    @Override
    public int deleteByExample(Object example) {
        return 0;
    }

    @Override
    public List selectByExample(Object example) {
        return null;
    }

    @Override
    public int selectCountByExample(Object example) {
        return 0;
    }

    @Override
    public Object selectOneByExample(Object example) {
        return null;
    }

    @Override
    public int updateByExample(Object record, Object example) {
        return 0;
    }

    @Override
    public int updateByExampleSelective(Object record, Object example) {
        return 0;
    }

    @Override
    public List selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List selectByRowBounds(Object record, RowBounds rowBounds) {
        return null;
    }
}
