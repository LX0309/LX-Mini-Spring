package com.lx.springframework.beans.factory;

/**
 * 标记接口，指示一个 Bean 有资格被 Spring 容器通过回调方法通知。
 * 回调方法的实际签名由各个子接口决定，但通常应该只包含一个单一参数的无返回值方法。
 * 这些子接口允许 Bean 在容器中感知或与特定的框架对象交互，例如 ApplicationContext、BeanFactory 等。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface Aware {
    // 接口本身不声明任何方法，它作为其他 Aware 相关接口的父接口。
}

