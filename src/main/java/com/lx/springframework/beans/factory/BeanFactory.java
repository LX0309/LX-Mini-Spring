package com.lx.springframework.beans.factory;

import com.lx.springframework.beans.BeansException;

/**
 * Bean工厂
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface BeanFactory {
    /**
     * 根据 Bean 的名称获取 Bean 实例。
     * 如果 Bean 尚未创建，并且容器配置为允许懒加载，则此方法将不立即创建 Bean，
     * 而是在实际需要时才创建。
     *
     * @param name Bean 的名称
     * @return Bean 实例
     * @throws BeansException 如果 Bean 无法被找到或者在创建过程中发生错误
     */
    Object getBean(String name) throws BeansException;

    /**
     * 重载的 getBean 方法，允许传递参数给具有参数的构造器或工厂方法，
     * 以实例化 Bean。
     *
     * @param name         Bean 的名称
     * @param args         构造器参数
     * @return Bean 实例
     * @throws BeansException 自定义异常，如果 Bean 创建失败或找不到对应的 Bean
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 泛型方法，根据 Bean 的名称和所需的类型获取特定类型的 Bean 实例。
     * 这允许调用者明确指定期望的返回类型。
     *
     * @param <T>          期望的返回类型
     * @param name         Bean 的名称
     * @param requiredType 期望返回的类型
     * @return 特定类型的 Bean 实例
     * @throws BeansException 如果 Bean 无法被找到或创建失败
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    /**
     * 获取给定类型的 Bean 实例，不指定 Bean 的名称。
     * 如果容器中有多个 Bean 匹配给定的类型，则可以通过返回多个实例或抛出异常来处理。
     *
     * @param <T>          期望的返回类型
     * @param requiredType 期望返回的类型
     * @return 特定类型的 Bean 实例
     * @throws BeansException 如果找不到匹配的 Bean 或者找到多个匹配的 Bean
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;

}
