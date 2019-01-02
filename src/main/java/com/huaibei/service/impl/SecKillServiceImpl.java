package com.huaibei.service.impl;

import com.huaibei.exception.SellException;
import com.huaibei.service.RedisLock;
import com.huaibei.service.SeckIllService;
import com.huaibei.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/28 11:57 AM
 * @Version: 1.0
 */
@Service
public class SecKillServiceImpl implements SeckIllService{

    private static final int TIME_OUT = 10 * 1000;//超时时间 10s
    @Autowired
    private RedisLock redisLock;
    /**
     * 国庆活动 特价限量100000份
     */
    static Map<String,Integer> products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;
    static {
        /**
         * 模拟多个表 商品表 库存表 秒杀成功表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456",100000);
        stock.put("123456",100000);
    }

    private String queryMap(String productId){
        return "国庆活动，特价 限量" + products.get(productId) +
                "份," + "还剩:" + stock.get(productId) + "份," +
                "该商品成功下单用户数目:" + orders.size() + "人";
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap("123456");
//        return null;
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        //加锁
        long time = System.currentTimeMillis() + TIME_OUT;
        if(!redisLock.lock(productId,String.valueOf(time))){
            throw new SellException(101,"人太多了，换个姿势试试");
        }

        //1 查询该商品库存 0位结束
        int stockNum = stock.get(productId);
        if(stockNum == 0){
            throw new SellException(100,"活动结束");
        }else {
            //2 下单(模拟不同的用户openid不同)
            orders.put(KeyUtil.genUniqueKey(),productId);
            //3 减库存
            stockNum = stockNum -1;
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
        }

        redisLock.unlock(productId,String.valueOf(time));
    }
}
