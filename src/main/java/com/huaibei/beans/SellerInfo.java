package com.huaibei.beans;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/25 2:01 PM
 * @Version: 1.0
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
