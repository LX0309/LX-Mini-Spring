package com.lx.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 用于注入值的注解，表示被注解的元素应该被注入指定的值。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    /**
     * 实际的值表达式，例如："#{systemProperties.myProp}"。
     *
     * @return 值表达式
     */
    String value();

}
