package com.huaibei.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -6589376663628721168L;
    private Integer code;

    private String msg;

    private T data;
}
