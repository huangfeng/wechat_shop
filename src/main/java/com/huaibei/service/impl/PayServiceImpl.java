package com.huaibei.service.impl;

import com.huaibei.dto.OrderMasterDTO;
import com.huaibei.enmus.ResultEnum;
import com.huaibei.exception.SellException;
import com.huaibei.service.OrderMasterService;
import com.huaibei.service.PayService;
import com.huaibei.utils.JsonUtil;
import com.huaibei.utils.MathUtil;
import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/20 10:13 AM
 * @Version: 1.0
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService{
    private static final String ORDER_NAME = "微信点餐订单";
    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public PayResponse create(OrderMasterDTO orderMasterDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderMasterDTO.getBuyerOpenid());
        payRequest.setOrderId(orderMasterDTO.getOrderId());
        payRequest.setOrderAmount(orderMasterDTO.getOrderAmount().doubleValue());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[发起支付] payRequest={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("[发起支付] payResponse={}",JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderMasterDTO orderMasterDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderMasterDTO.getOrderId());
        refundRequest.setOrderAmount(orderMasterDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信退款] refundRequest={}",JsonUtil.toJson(refundRequest));
        RefundResponse refund = bestPayService.refund(refundRequest);
        log.info("[微信退款] refundResponse={}",JsonUtil.toJson(refund));
        return refund;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //验证签名
        //支付的状态

        //支付金额
        //支付人（下单人 == 支付人）

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("[微信支付] 异步通知 payResponse={}",payResponse);
        //查询订单是否存在
        OrderMasterDTO one = orderMasterService.findOne(payResponse.getOrderId());
        if(one == null){
            log.error("[微信支付] 异步通知, 订单不存在, orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXEST);
        }
        //判断金额是否一致
//        if(!one.getOrderAmount().equals(payResponse.getOrderAmount())){
//        if(one.getOrderAmount().compareTo(new BigDecimal(payResponse.getOrderAmount())) == 0){
        if(!MathUtil.equals(payResponse.getOrderAmount(),one.getOrderAmount().doubleValue())){
            log.error("[微信支付] 异步通知, 订单金额不一致, 微信通知金额 = {}, 系统金额 = {}",payResponse.getOrderAmount(),one.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //修改订单支付状态
        OrderMasterDTO paid = orderMasterService.paid(one);
        return payResponse;
    }
}
