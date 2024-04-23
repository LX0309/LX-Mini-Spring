package com.lx.springframework.beans.factory;



import com.lx.springframework.beans.BeansException;

import java.util.Map;

/**
 * 提供了按类型检索 Bean 集合的功能以及获取注册表中所有 Bean 名称的方法
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 根据给定类型返回所有匹配的 Bean 实例的映射。
     * 此方法允许通过类型（而不仅仅是名称）来检索 Bean，并且可以返回多个 Bean 实例。
     * 如果没有找到匹配的 Bean，则返回一个空的映射。
     *
     * @param <T>   期望的 Bean 类型
     * @param type  要查找的 Bean 类型
     * @return 一个包含 Bean 名称和 Bean 实例的映射，其中 Bean 名称是键，Bean 实例是值
     * @throws BeansException 如果查找过程中发生错误
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有定义的 Bean 名称的数组。
     * 此方法提供了一种获取容器中所有 Bean 名称的方式，而不需要知道它们的类型或作用域。
     *
     * @return 包含所有 Bean 名称的字符串数组
     */
    String[] getBeanDefinitionNames();

}
