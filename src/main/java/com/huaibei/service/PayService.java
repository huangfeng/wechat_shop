package com.huaibei.service;

import com.huaibei.dto.OrderMasterDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/20 10:12 AM
 * @Version: 1.0
 */
public interface PayService {
    PayResponse create(OrderMasterDTO orderMasterDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderMasterDTO orderMasterDTO);
}
