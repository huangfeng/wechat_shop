package com.huaibei.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/17 3:32 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法。。。code = {}",code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx680b8c9999a557d3&secret=1411f8069ef85c13baa59725a5c60b04&code="+ code +"&grant_type=authorization_code";

        RestTemplate restTemplate =  new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
        log.info("response = {}",response);
    }

}
