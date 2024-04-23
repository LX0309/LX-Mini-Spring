package com.lx.springframework.beans.factory.support;


import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.config.BeanDefinition;

/**
 * BeanDefinitionRegistry 接口定义了对 Bean 定义注册表进行操作的方法。
 * 注册表用于存储和管理 Bean 的定义信息。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition。
     *
     * @param beanName       Bean 名称
     * @param beanDefinition Bean 定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 使用 Bean 名称查询对应的 BeanDefinition。
     *
     * @param beanName Bean 名称
     * @return 对应的 BeanDefinition
     * @throws BeansException 如果无法找到对应的 BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断注册表中是否包含指定名称的 BeanDefinition。
     *
     * @param beanName Bean 名称
     * @return 如果包含指定名称的 BeanDefinition，则返回 true；否则返回 false
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回注册表中所有的 Bean 名称。
     *
     * @return 所有的 Bean 名称数组
     */
    String[] getBeanDefinitionNames();

}
