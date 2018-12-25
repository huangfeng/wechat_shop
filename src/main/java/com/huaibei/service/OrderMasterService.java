package com.huaibei.service;

import com.huaibei.beans.OrderMaster;
import com.huaibei.dto.OrderMasterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/13 9:53 AM
 * @Version: 1.0
 */
public interface OrderMasterService {

    //创建订单
    OrderMasterDTO create(OrderMasterDTO orderMasterDTO);

    //查询单个订单
    OrderMasterDTO findOne(String orderId);

    //查询订单列表
    Page<OrderMasterDTO> findList(String buyerOpenid, Pageable pageable);

    //取消订单
    OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO);

    //完结订单
    OrderMasterDTO finish(OrderMasterDTO orderMasterDTO);

    //支付订单
    OrderMasterDTO paid(OrderMasterDTO orderMasterDTO);

    //查询分页订单列表
    Page<OrderMasterDTO> findList(Pageable pageable);

}
