package com.huaibei.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huaibei.enmus.ProductStatusEnmu;
import com.huaibei.utils.EnumUtil;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@ToString
public class ProductInfo {

    @Id
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

    //状态
    private Integer productStatus = ProductStatusEnmu.UP.getCode();

    //类型
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnmu getProductStatusEnmu(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnmu.class);
    }


}
