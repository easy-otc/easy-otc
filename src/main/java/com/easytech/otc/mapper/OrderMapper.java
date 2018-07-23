package com.easytech.otc.mapper;

import org.apache.ibatis.annotations.Param;

import com.easytech.otc.common.mybatis.plugin.OtcMapper;
import com.easytech.otc.mapper.model.Order;

public interface OrderMapper extends OtcMapper<Order> {

    void orderSuccessOnChain(@Param("txHash") String txHash);

    void orderSuccessOnNet(@Param("txHash") String txHash);
}