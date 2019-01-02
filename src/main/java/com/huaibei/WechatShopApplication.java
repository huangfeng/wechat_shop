package com.huaibei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.huaibei.beans.mapper")
@EnableCaching
public class WechatShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatShopApplication.class, args);
    }
}
