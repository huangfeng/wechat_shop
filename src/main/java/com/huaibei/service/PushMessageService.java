package com.huaibei.service;

import com.huaibei.dto.OrderMasterDTO;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/27 11:15 AM
 * @Version: 1.0
 */
public interface PushMessageService {
    //推送要设置ip白名单
    void orderStatus(OrderMasterDTO orderMasterDTO);
}
