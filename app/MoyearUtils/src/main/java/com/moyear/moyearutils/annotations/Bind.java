package com.moyear.moyearutils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version V1.0
 * @Author : Moyear
 * @Time : 2022/1/6 18:55
 * @Description : Vie绑定注释
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bind {
    int value();
}
