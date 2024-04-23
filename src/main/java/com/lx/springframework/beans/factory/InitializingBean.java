package com.lx.springframework.beans.factory;

/**
 * 允许实现该接口的 Bean 在属性设置之后（即所有的依赖注入完成后）执行自定义初始化逻辑
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface InitializingBean {

    /**
     * 在所有属性填充完成后（依赖注入完成后），由 Spring 容器调用此方法。
     * 此方法允许 Bean 执行初始化逻辑，如验证依赖关系、启动服务等。
     * 此方法通常用于执行那些不能在构造器中完成的初始化操作。
     *
     * @throws Exception 如果初始化过程中发生任何异常，则抛出异常。
     *                   这些异常将被 Spring 容器捕获并处理，可能导致容器启动失败。
     */
    void afterPropertiesSet() throws Exception;

}