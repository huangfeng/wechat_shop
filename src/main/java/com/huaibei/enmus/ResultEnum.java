package com.huaibei.enmus;

import lombok.Getter;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/13 10:39 AM
 * @Version: 1.0
 */
@Getter
public enum  ResultEnum {

    PRODUCT_NOT_EXIST(-1,"商品不存在"),

    PRUDUCT_STORK_ERROR(-2,"库存不正确"),

    ORDER_NOT_EXEST(-3,"订单不存在"),

    ORDERDETAIL_NOT_EXEST(-4,"订单详情不存在"),

    ORDER_STATUS_ERROR(-5,"订单状态不正确"),

    ORDER_UPDATE_FAIL(-6,"订单更新失败"),

    ORDER_DETAIL_EMPTY(-7,"订单中无商品"),

    ORDER_PAY_STATUS_ERROR(-8,"订单支付状态不正确")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
