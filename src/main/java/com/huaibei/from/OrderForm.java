package com.huaibei.from;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/17 9:45 AM
 * @Version: 1.0
 */
@Data
public class OrderForm {

    @NotNull(message = "姓名不能位空")
    private String name;

    @NotNull(message = "手机不能为空")
    private String phone;

    @NotNull(message = "地址不能为空")
    private String address;

    @NotNull(message = "openid不能位空")
    private String openid;

    @NotNull(message = "购物车不能为空")
    private String items;

}
