package com.lx.springframework.beans.factory;

/**
 * 实现此接口，既能感知到所属的 ClassLoader
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface BeanClassLoaderAware extends Aware {

    /**
     * 设置用于加载 Bean 类的类加载器。
     * 此方法由 Spring 容器在 Bean 实例化和初始化之后调用，
     * 允许 Bean 明确地访问类加载器，例如用于加载其他类或资源。
     *
     * @param classLoader 用于加载 Bean 的类加载器实例
     */
    void setBeanClassLoader(ClassLoader classLoader);

}
