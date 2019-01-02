package com.huaibei.exception;

import com.huaibei.enmus.ResultEnum;
import lombok.Data;
import lombok.Getter;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/13 10:39 AM
 * @Version: 1.0
 */
@Getter
public class SellException extends  RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String msg) {
        super(msg);
        this.code = code;
    }
}
