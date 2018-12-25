package com.huaibei.dao;

import com.huaibei.beans.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/25 2:03 PM
 * @Version: 1.0
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo,String>{

    SellerInfo findByOpenid(String openid);
}
