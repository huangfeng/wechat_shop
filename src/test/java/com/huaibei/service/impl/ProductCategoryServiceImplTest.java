package com.huaibei.service.impl;

import com.huaibei.beans.ProductCategory;
import com.huaibei.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {


    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory one = productCategoryService.findOne(1);
        assertNotNull(one);
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> all = productCategoryService.findAll();
        assertNotEquals(0,all.size());


    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> byCategoryTypeIn = productCategoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertNotEquals(0,byCategoryTypeIn.size());
    }

    @Test
    @Transactional
    public void save() throws Exception {
        ProductCategory ProductCategory = new ProductCategory("nansengzhuanshu",22,new Date(),new Date());
        ProductCategory result = productCategoryService.save(ProductCategory);
        assertNotNull(result);
    }

}