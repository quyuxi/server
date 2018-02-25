package com.resthome.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 一缕仙缘 on 2017-07-06.
 * @Description : 用来判断是否进行加密传输
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SerializedField {

    boolean encode() default true;
}
