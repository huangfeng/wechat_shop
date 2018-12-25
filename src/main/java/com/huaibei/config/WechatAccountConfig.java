package com.huaibei.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/18 1:16 PM
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String mpAppId;

    private String mpAppSecret;

    //商户号
    private String mchId;

    //商户密匙
    private String mchKey;

    //商户证书路径
    private String keyPath;

    //异步通知
    private String notifyUrl;
}
