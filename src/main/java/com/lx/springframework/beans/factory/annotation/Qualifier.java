package com.lx.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 限定符注解，用于为自动装配提供限定符。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    /**
     * 限定符值，用于指定自动装配时的限定条件。
     *
     * @return 限定符值
     */
    String value() default "";

}
