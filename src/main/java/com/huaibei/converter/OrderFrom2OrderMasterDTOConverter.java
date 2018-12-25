package com.huaibei.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huaibei.beans.OrderDetail;
import com.huaibei.dto.OrderMasterDTO;
import com.huaibei.enmus.ResultEnum;
import com.huaibei.exception.SellException;
import com.huaibei.from.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/17 9:59 AM
 * @Version: 1.0
 */
@Slf4j
public class OrderFrom2OrderMasterDTOConverter {
    public static OrderMasterDTO convert(OrderForm orderForm){
        Gson gson = new Gson();

        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        orderMasterDTO.setBuyerName(orderForm.getName());
        orderMasterDTO.setBuyerPhone(orderForm.getPhone());
        orderMasterDTO.setBuyerAddress(orderForm.getAddress());
        orderMasterDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        }catch (Exception e){
            log.error("[对象转换] 错误, String={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderMasterDTO.setOrderDetailList(orderDetailList);

        return orderMasterDTO;
    }
}
