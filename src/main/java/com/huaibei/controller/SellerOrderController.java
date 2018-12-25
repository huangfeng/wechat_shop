package com.huaibei.controller;

import com.huaibei.dto.OrderMasterDTO;
import com.huaibei.enmus.ResultEnum;
import com.huaibei.exception.SellException;
import com.huaibei.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/21 9:47 AM
 * @Version: 1.0
 */

@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<OrderMasterDTO> orderMasterDTOPage = orderMasterService.findList(pageRequest);
        map.put("orderMasterDTOPage", orderMasterDTOPage);
        log.info("orderMasterDTOPage = {}",orderMasterDTOPage);
        return new ModelAndView("/order/list", map);

    }

    @RequestMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){

        try {
            OrderMasterDTO one = orderMasterService.findOne(orderId);
            orderMasterService.cancel(one);
        }catch (SellException e){
            log.error("【卖家端取消订单】发生异常 e = ",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("/common/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("/common/success",map);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,Map<String,Object> map){
        try {
            OrderMasterDTO orderMasterDTO = orderMasterService.findOne(orderId);
            map.put("orderMasterDTO",orderMasterDTO);
        }catch (SellException e){
            log.error("【卖家端订单查询】发生异常 e = ",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("/common/error",map);
        }

        return new ModelAndView("/order/detail",map);
    }


    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId,Map<String,Object> map){
        try {
            OrderMasterDTO one = orderMasterService.findOne(orderId);
            orderMasterService.finish(one);
        }catch (SellException e){
            log.error("【卖家端订单取消】发生异常 e ={} ",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("/common/error",map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("/common/success",map);
    }
}
