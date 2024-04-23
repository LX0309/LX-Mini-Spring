package com.lx.springframework.beans.factory;

/**
 * 实现此接口，既能感知到所属的 BeanName
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface BeanNameAware extends Aware {

    /**
     * 设置 Bean 的名称。
     * 此方法由 Spring 容器在 Bean 实例化之后、初始化之前调用，
     * 允许 Bean 知道自己在容器中注册的名字。
     *
     * @param name Bean 在 Spring 容器中注册的名称。
     *             如果同一个 Bean 有多个别名，此方法也会接受到这些别名之一。
     *             如果 Bean 没有明确设置名称或别名，那么将使用默认生成的名称。
     */
    void setBeanName(String name);

}
