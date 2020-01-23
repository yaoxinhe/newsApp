package com.example.bawei.baselibrary.MyInter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/12 14:31
 * @Email 1151403054@qq.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface YxhInjectView {
    int id() default -1;
}
