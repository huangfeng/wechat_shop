package com.huaibei.service.impl;

import com.huaibei.dto.OrderMasterDTO;
import com.huaibei.enmus.ResultEnum;
import com.huaibei.exception.SellException;
import com.huaibei.service.BuyerService;
import com.huaibei.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/17 2:02 PM
 * @Version: 1.0
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    private OrderMasterService orderMasterService;
    @Override
    public OrderMasterDTO findOrderMaster(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderMasterDTO cancelOrderMaster(String openid, String orderId) {
        OrderMasterDTO orderMasterDTO = checkOrderOwner(openid, orderId);
        if(orderMasterDTO == null){
            log.error("[取消订单] 查不到该订单 orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXEST);
        }
        return orderMasterService.cancel(orderMasterDTO);
    }

    private OrderMasterDTO checkOrderOwner(String openid, String orderId){
        OrderMasterDTO one = orderMasterService.findOne(orderId);
        if(!one.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的openid不一致 openid={}, orderMasterDTO={}",openid,one);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return one;
    }
}
