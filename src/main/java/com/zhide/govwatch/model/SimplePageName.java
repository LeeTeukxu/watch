package com.zhide.govwatch.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SimplePageName {
    /**
     * 客户端页面名称
     *
     * @return
     */
    String name() default "";
}
