package com.huaibei.dao;

import com.huaibei.beans.ProductInfo;
import com.lly835.bestpay.utils.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void saveTest() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("10000");
        productInfo.setProductName("哇哈哈");
        productInfo.setProductPrice(new BigDecimal(5.5));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("哈哈哈");
        productInfo.setProductIcon("http://www.xxxxx.png");
        productInfo.setCategoryType(1);
        ProductInfo info = productInfoDao.save(productInfo);
        assertNotNull(info);

    }


    @Test
    public void findByCategoryType() throws Exception {

        ProductInfo one = productInfoDao.getOne("10000");
        assertNotNull(one);

    }
}