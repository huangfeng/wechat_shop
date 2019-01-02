package com.huaibei.service.impl;

import com.huaibei.beans.SellerInfo;
import com.huaibei.service.SellerInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/25 4:24 PM
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {
    @Autowired
    private SellerInfoService sellerInfoService;
    @Test
    public void findSellerInfoByOpenid() throws Exception {
        SellerInfo ooo = sellerInfoService.findSellerInfoByOpenid("ooo");
        Assert.assertEquals("ooo",ooo.getOpenid());

    }

}