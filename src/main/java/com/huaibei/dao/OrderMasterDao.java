package com.huaibei.dao;

import com.huaibei.beans.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/12 9:31 PM
 * @Version: 1.0
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String>{
    Page<OrderMaster> findByBuyerOpenid(String openid, Pageable pageable);
}
