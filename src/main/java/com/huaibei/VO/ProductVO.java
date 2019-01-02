package com.huaibei.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductVO  implements Serializable {

    private static final long serialVersionUID = -6176020318367552874L;
    @JsonProperty("name")
    private String  categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> categoryFoods;
}
