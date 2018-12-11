package com.huaibei.dao;

import com.huaibei.beans.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018-12-10$ 15:55$
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findOne(){
        ProductCategory one = productCategoryDao.findOne(1);
        System.out.println(one);
    }

    @Test
    public void findList(){
        List<ProductCategory> one = productCategoryDao.findByCategoryTypeIn(Arrays.asList(2,10));
        System.out.println(one);
    }

    @Test
    public void saveOne(){
        ProductCategory productCategory = productCategoryDao.findOne(3);
        productCategory.setCategoryType(10);
        productCategoryDao.save(productCategory);
    }
}