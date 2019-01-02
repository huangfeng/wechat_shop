package com.huaibei.service.impl;

import com.huaibei.beans.SellerInfo;
import com.huaibei.dao.SellerInfoDao;
import com.huaibei.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/25 4:22 PM
 * @Version: 1.0
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService {
    @Autowired
    private SellerInfoDao sellerInfoDao;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {

        return sellerInfoDao.findByOpenid(openid);
    }
}
