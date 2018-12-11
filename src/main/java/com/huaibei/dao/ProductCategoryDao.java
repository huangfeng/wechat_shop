package com.huaibei.dao;

import com.huaibei.beans.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018-12-10$ 15:54$
 * @Version: 1.0
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}
