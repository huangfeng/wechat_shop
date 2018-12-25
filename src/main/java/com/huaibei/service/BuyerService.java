package com.huaibei.service;

import com.huaibei.dto.OrderMasterDTO;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/17 1:59 PM
 * @Version: 1.0
 */
public interface BuyerService {

    OrderMasterDTO findOrderMaster(String openid,String orderId);

    OrderMasterDTO cancelOrderMaster(String openid,String orderId);
}
