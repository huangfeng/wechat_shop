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

    SUCCESS(0,"成功"),

    PRODUCT_NOT_EXIST(-1,"商品不存在"),

    PRUDUCT_STORK_ERROR(-2,"库存不正确"),

    ORDER_NOT_EXEST(-3,"订单不存在"),

    ORDERDETAIL_NOT_EXEST(-4,"订单详情不存在"),

    ORDER_STATUS_ERROR(-5,"订单状态不正确"),

    ORDER_UPDATE_FAIL(-6,"订单更新失败"),

    ORDER_DETAIL_EMPTY(-7,"订单中无商品"),

    ORDER_PAY_STATUS_ERROR(-8,"订单支付状态不正确"),

    PARAM_ERROR(-9,"参数不正确"),

    CART_EMPTY(-10,"购物车不能位空"),

    ORDER_OWNER_ERROR(-11,"该订单不属于当前用户"),

    WECHAT_MP_ERROR(-12,"微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(-13,"微信支付金额与异步通知金额不一致"),

    ORDER_CANCEL_SUCCESS(-14,"订单取消成功"),

    LOGIN_FAIL(-15,"登录失败,登录信息不正确"),

    LOGOUT_SUCCESS(-16,"登出成功"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
