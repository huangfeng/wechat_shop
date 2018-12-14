package com.huaibei.dao;

import com.huaibei.beans.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/12 9:12 PM
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("200000");
        orderDetail.setOrderId("300000");
        orderDetail.setProductIcon("xxxxx.png");
        orderDetail.setProductName("tom");
        orderDetail.setProductPrice(new BigDecimal(3.3));
        orderDetail.setProductQuantity(100);
        orderDetail.setProductId("11111");
        OrderDetail save = orderDetailDao.save(orderDetail);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByOrderId() throws Exception {
    }

}