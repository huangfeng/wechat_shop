package com.huaibei;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
//@Data
public class WechatShopApplicationTests {

    //private final Logger logger = LoggerFactory.getLogger(WechatShopApplicationTests.class);
    @Test
    public void contextLoads() {
        String name = "AA";
        int age = 11;
        log.info("name : {}, age : {} ",name,age);
        log.error("error...");
        log.info("info...");
        log.debug("debug...");
    }

}
