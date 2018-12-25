package com.huaibei.dao;

import com.huaibei.beans.SellerInfo;
import com.huaibei.utils.KeyUtil;
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
 * @CreateDate: 2018/12/25 2:05 PM
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Test
    public void findByOpenid() throws Exception {
        SellerInfo sellerInfo = sellerInfoDao.findByOpenid("ooo");
        Assert.assertEquals("ooo", sellerInfo.getOpenid());
    }
    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        System.out.println(KeyUtil.genUniqueKey());
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("ooo");
        SellerInfo result = sellerInfoDao.save(sellerInfo);
        Assert.assertNotNull(result);
    }
}