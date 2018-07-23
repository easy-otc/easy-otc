package com.easytech.otc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easytech.otc.mapper.OrderMapper;
import com.easytech.otc.mapper.model.Order;

@Service
public class OrderService extends BaseService<Order> {

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public void orderSuccessOnChain(String txHash) {
        orderMapper.orderSuccessOnChain(txHash);
    }

    @Transactional
    public void orderSuccessOnNet(String txHash) {
        orderMapper.orderSuccessOnNet(txHash);
    }
}