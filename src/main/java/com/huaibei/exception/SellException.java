package com.huaibei.exception;

import com.huaibei.enmus.ResultEnum;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/13 10:39 AM
 * @Version: 1.0
 */
public class SellException extends  RuntimeException{

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }
}
