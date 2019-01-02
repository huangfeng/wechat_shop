package com.huaibei.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/26 10:39 AM
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlConfig {

    //微信公众平台授权url
    public String wechatMpAuthorize;

    //微信开放平台授权url
    public String wechatOpenAuthorize;

    //系统url
    public String sell;
}
