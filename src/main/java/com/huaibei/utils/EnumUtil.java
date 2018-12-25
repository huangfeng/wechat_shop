package com.huaibei.utils;

import com.huaibei.enmus.CodeEnum;
import com.sun.org.apache.regexp.internal.REUtil;
import org.aopalliance.reflect.Code;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/21 10:52 AM
 * @Version: 1.0
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return  null;
    }
}
