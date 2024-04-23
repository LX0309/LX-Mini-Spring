package com.lx.springframework.stereotype;

import java.lang.annotation.*;

/**
 * Component 注解用于标记类为组件。
 * 当一个类被标记为组件时，表示它是应用程序中的一个组成部分，可以被容器管理和使用。
 * 可以通过 value 属性指定组件的名称。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
@Target(ElementType.TYPE) // 该注解可以用在类上
@Retention(RetentionPolicy.RUNTIME) // 该注解会在运行时保留
@Documented
public @interface Component {

    /**
     * 组件的名称，默认为空字符串。
     *
     * @return 组件的名称
     */
    String value() default "";
}

