package com.huaibei.controller;

import com.huaibei.dto.OrderMasterDTO;
import com.huaibei.enmus.ResultEnum;
import com.huaibei.exception.SellException;
import com.huaibei.service.OrderMasterService;
import com.huaibei.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/20 10:07 AM
 * @Version: 1.0
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId")  String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map){
        //查询订单
        OrderMasterDTO one = orderMasterService.findOne(orderId);
        if(one == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXEST);
        }
        //发起支付
//        PayResponse payResponse = payService.create(one);
//        map.put("payResponse",payResponse);
        return  new ModelAndView("/pay/create",map);
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        return new ModelAndView("/pay/success");
    }
}
