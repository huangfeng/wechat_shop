package com.huaibei.service;

import com.huaibei.beans.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
