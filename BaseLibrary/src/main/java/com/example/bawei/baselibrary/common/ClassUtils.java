package com.example.bawei.baselibrary.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/15 17:00
 * @Email 1151403054@qq.com
 */
public class ClassUtils {
    /**
     * 获取泛型类型
     * @param obj
     * @return
     */
    public static Class<?> getParameterizedClazz(Object obj){
        Type t= obj.getClass().getGenericSuperclass();
        ParameterizedType p=(ParameterizedType)t;
        Class c=(Class) p.getActualTypeArguments()[0];
        return c;
    }
}
