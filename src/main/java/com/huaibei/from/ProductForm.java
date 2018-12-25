package com.huaibei.from;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/25 10:28 AM
 * @Version: 1.0
 */
@Data
public class ProductForm {

    private String productId;

    //商品名称
    private String productName;

    //商品价格
    private BigDecimal productPrice;

    //库存
    private Integer productStock;

    //描述
    private String productDescription;

    //图标
    private String productIcon;

    //类型
    private Integer categoryType;

}
