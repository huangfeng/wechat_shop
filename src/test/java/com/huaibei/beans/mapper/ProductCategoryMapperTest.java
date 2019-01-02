package com.huaibei.beans.mapper;

import com.huaibei.beans.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/28 9:58 AM
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Test
    public void insertByMap() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("category_name","test5");
        map.put("category_type","55");
        int result = productCategoryMapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }

    @Test
    public void insertByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("test7");
        productCategory.setCategoryType(77);
        int result = productCategoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByCategoryType(){
        ProductCategory result = productCategoryMapper.findByCategoryType(66);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryName(){
        List<ProductCategory> result = productCategoryMapper.findByCategoryName("test7");
        Assert.assertNotNull(result);
    }


    @Test
    public void updateByCategoryType(){
        int result = productCategoryMapper.updateByCategoryType("test6", 66);
        Assert.assertEquals(1,result);
    }

    @Test
    public void updateByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("test6");
        productCategory.setCategoryType(77);
        int result = productCategoryMapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteByCategoryType(){
        int i = productCategoryMapper.deleteByCategoryType(77);
        Assert.assertEquals(1,i);
    }

    @Test
    public void selectByCategoryType(){
        ProductCategory result = productCategoryMapper.selectByCategoryType(66);
        Assert.assertNotNull(result);
    }
}