package com.huaibei.controller;

import com.huaibei.VO.ResultVO;
import com.huaibei.converter.OrderFrom2OrderMasterDTOConverter;
import com.huaibei.dto.OrderMasterDTO;
import com.huaibei.enmus.ResultEnum;
import com.huaibei.exception.SellException;
import com.huaibei.from.OrderForm;
import com.huaibei.service.BuyerService;
import com.huaibei.service.OrderMasterService;
import com.huaibei.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/17 9:41 AM
 * @Version: 1.0
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String >> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确, orderFrom={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderMasterDTO orderMasterDTO = OrderFrom2OrderMasterDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderMasterDTO.getOrderDetailList())){
            log.error("[创建订单] 购物车不能位空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderMasterDTO result = orderMasterService.create(orderMasterDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderMasterDTO>> list(@RequestParam("openid") String openid,
                                               @RequestParam(value = "page",defaultValue = "0") Integer page,
                                               @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("[查询订单列表] openid 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page,size);
        Page<OrderMasterDTO> list = orderMasterService.findList(openid, pageRequest);

        return ResultVOUtil.success(list.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderMasterDTO> detail(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        OrderMasterDTO one = buyerService.findOrderMaster(openid,orderId);
        return ResultVOUtil.success(one);
    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        buyerService.cancelOrderMaster(openid,orderId);
        return ResultVOUtil.success();
    }

}
