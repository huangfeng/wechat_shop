package com.huaibei.dao;

import com.huaibei.beans.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/12 4:26 PM
 * @Version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void saveTest() throws Exception {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("10001");
        orderMaster.setBuyerName("rhy");
        orderMaster.setBuyerAddress("shanghai");
        orderMaster.setBuyerOpenid("10000000000");
        orderMaster.setBuyerPhone("15000000000");
        orderMaster.setOrderAmount(new BigDecimal(2.3));
        OrderMaster master = orderMasterDao.save(orderMaster);
        Assert.assertNotNull(master);
    }

}