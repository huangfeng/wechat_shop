package com.huaibei.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/13 11:20 AM
 * @Version: 1.0
 */
@Data
public class CartDTO {

    //商品id
    private String productId;

    //数量
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
