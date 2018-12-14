package com.huaibei.dao;

import com.huaibei.beans.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoDao extends JpaRepository<ProductInfo,String >{
    List<ProductInfo> findByProductStatus(Integer categoryType);
}
