package com.huaibei.service;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/28 1:05 PM
 * @Version: 1.0
 */
public interface SeckIllService {

    String querySecKillProductInfo(String productId);

    void orderProductMockDiffUser(String productId);
}
