package com.huaibei.enmus;

import lombok.Getter;

@Getter
public enum ProductStatusEnmu implements CodeEnum{
    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String msg;

    ProductStatusEnmu(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
