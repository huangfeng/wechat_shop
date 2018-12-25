package com.huaibei.service.impl;

import com.huaibei.dto.OrderMasterDTO;
import com.huaibei.service.OrderMasterService;
import com.huaibei.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/20 10:28 AM
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {
    @Autowired
    private PayService payService;

    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    public void create() throws Exception {
        OrderMasterDTO orderMasterDTO = orderMasterService.findOne("1545014609890705433");
        payService.create(orderMasterDTO);

    }

    @Test
    public void refund(){
        OrderMasterDTO one = orderMasterService.findOne("1545014609890705433");
        payService.refund(one);
    }


}