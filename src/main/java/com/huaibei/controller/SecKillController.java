package com.huaibei.controller;

import com.huaibei.service.SeckIllService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/28 1:15 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {

    @Autowired
    private SeckIllService seckIllService;

    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId){
        return seckIllService.querySecKillProductInfo(productId);
    }

    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId){
        log.info("@skill request, productId: {}",productId);
        seckIllService.orderProductMockDiffUser(productId);
        return seckIllService.querySecKillProductInfo(productId);
    }
}
