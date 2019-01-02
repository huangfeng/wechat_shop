package com.huaibei.beans.dao;

import com.huaibei.beans.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/28 10:59 AM
 * @Version: 1.0
 */
public class ProductCategoryDao {
    @Autowired
    private ProductCategoryMapper mapper;

    public int insertByMap(Map<String,Object> map){
        return mapper.insertByMap(map);
    }
}
