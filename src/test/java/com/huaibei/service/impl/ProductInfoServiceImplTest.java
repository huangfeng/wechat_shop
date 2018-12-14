package com.huaibei.service.impl;

import com.huaibei.beans.ProductInfo;
import com.huaibei.enmus.ProductStatusEnmu;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Test
    public void findOne() throws Exception {
        ProductInfo one = productInfoService.findOne("10000");
        Assert.assertEquals("10000",one.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> All = productInfoService.findUpAll();
        Assert.assertNotEquals(0,All.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> all = productInfoService.findAll(pageRequest);
        Assert.assertNotEquals(0,all.getSize());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("10001");
        productInfo.setProductName("爽歪歪");
        productInfo.setProductPrice(new BigDecimal(6.5));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("歪歪歪");
        productInfo.setProductIcon("http://www.xxxxx.png");
        productInfo.setCategoryType(ProductStatusEnmu.DOWN.getCode());
        productInfoService.save(productInfo);
    }

}