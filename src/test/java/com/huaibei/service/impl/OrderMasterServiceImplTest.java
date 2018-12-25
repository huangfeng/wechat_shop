package com.huaibei.service.impl;

import com.huaibei.beans.OrderDetail;
import com.huaibei.dto.OrderMasterDTO;
import com.huaibei.enmus.OrderStatusEnum;
import com.huaibei.enmus.PayStatusEnum;
import com.huaibei.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/13 11:54 AM
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterService orderMasterService;

    private final String BUYER_OPENID = "1111111";
    private final String ORDER_ID = "1544683170310911105";
    @Test
    public void create() throws Exception {
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        orderMasterDTO.setBuyerName("tom");
        orderMasterDTO.setBuyerAddress("anhuihuaibei");
        orderMasterDTO.setBuyerPhone("15005610038");
        orderMasterDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("10001");
        o1.setProductQuantity(2);
        orderDetailList.add(o1);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("10000");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);
        orderMasterDTO.setOrderDetailList(orderDetailList);
        OrderMasterDTO result = orderMasterService.create(orderMasterDTO);
        log.info("[创建订单] result = {}",result);

    }


    @Test
    public void findOne() throws Exception {
        OrderMasterDTO one = orderMasterService.findOne(ORDER_ID);
        log.info("[查询单个订单] result = {}",one);


    }

    @Test
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<OrderMasterDTO> list = orderMasterService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0,list.getTotalElements());
    }

    @Test
    public void list() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<OrderMasterDTO> list = orderMasterService.findList(request);
        Assert.assertNotEquals(0,list.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderMasterDTO one = orderMasterService.findOne(ORDER_ID);
        OrderMasterDTO cancel = orderMasterService.cancel(one);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),cancel.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderMasterDTO one = orderMasterService.findOne(ORDER_ID);
        OrderMasterDTO cancel = orderMasterService.finish(one);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),cancel.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderMasterDTO one = orderMasterService.findOne(ORDER_ID);
        OrderMasterDTO cancel = orderMasterService.paid(one);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),cancel.getPayStatus());
    }

}