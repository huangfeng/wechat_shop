package com.huaibei.service;

import com.huaibei.beans.SellerInfo;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/25 4:21 PM
 * @Version: 1.0
 */
public interface SellerInfoService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
