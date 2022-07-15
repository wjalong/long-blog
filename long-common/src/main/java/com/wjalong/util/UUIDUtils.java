package com.wjalong.util;


import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description: 生成UUID
 * @author: wangjialong
 * @Date 2021/6/29 13:10
 */
@Component
public class UUIDUtils {

    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
