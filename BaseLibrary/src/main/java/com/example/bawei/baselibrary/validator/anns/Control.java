package com.example.bawei.baselibrary.validator.anns;


import com.example.bawei.baselibrary.validator.ValidatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Control {
    /**
     * 提示消息
     * @return
     */
    String Msg() default "出错啦";

    /**
     *
     * 校验类型
     * @return
     */
    ValidatorType CType() default ValidatorType.IsNull;

    /**
     * 比对验证 目标控件
     * @return
     */
    String EqualsTarget() default "";

    /**
     * 正则表达式
     * @return
     */
    String Regex() default "";
}

