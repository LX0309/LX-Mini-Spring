package com.lx.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解，用于定义作用域
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    /**
     * 定义作用域的字符串值，默认为 "singleton"。
     * 可用的配置包括：
     * "singleton" - 一个 IoC 容器中只有一个 Bean 实例。（本项目默认）
     * "prototype" - 每次请求都会创建一个新的 Bean 实例。
     *
     * @return 作用域的名称
     */
    String value() default "singleton";

}
