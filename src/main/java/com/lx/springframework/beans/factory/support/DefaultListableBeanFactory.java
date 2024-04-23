package com.lx.springframework.beans.factory.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.lx.springframework.beans.factory.config.BeanDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DefaultListableBeanFactory 是一个 Bean 工厂的默认实现类，实现了 BeanDefinitionRegistry 和 ConfigurableListableBeanFactory 接口。
 * 它继承自 AbstractAutowireCapableBeanFactory 类，并提供了对 BeanDefinition 的注册、查找以及对 Bean 的预初始化等功能。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    // 存储 BeanDefinition 的映射关系
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 将指定的 BeanDefinition 注册到注册表中。
     *
     * @param beanName       Bean 名称
     * @param beanDefinition Bean 定义
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * 判断注册表中是否包含指定名称的 BeanDefinition。
     *
     * @param beanName Bean 名称
     * @return 如果包含指定名称的 BeanDefinition，则返回 true；否则返回 false
     */
    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    /**
     * 根据给定的类型，获取注册表中所有对应的 Bean 实例。
     *
     * @param type Bean 类型
     * @param <T>  泛型类型
     * @return 对应类型的 Bean 实例映射
     * @throws BeansException 如果无法找到对应类型的 Bean 实例
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    /**
     * 获取注册表中所有的 Bean 名称。
     *
     * @return 所有的 Bean 名称数组
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    /**
     * 根据给定的 Bean 名称，获取对应的 BeanDefinition。
     *
     * @param beanName Bean 名称
     * @return 对应的 BeanDefinition
     * @throws BeansException 如果无法找到对应的 BeanDefinition
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    /**
     * 预初始化所有的单例 Bean 实例。
     *
     * @throws BeansException 如果预初始化过程中发生异常
     */
    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }

    /**
     * 根据给定的类型，获取对应的单例 Bean 实例。
     *
     * @param requiredType 要求的 Bean 类型
     * @param <T>          泛型类型
     * @return 对应类型的单例 Bean 实例
     * @throws BeansException 如果找到多个或者零个对应类型的 Bean 实例
     */
    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        List<String> beanNames = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Class<?> beanClass = entry.getValue().getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)) {
                beanNames.add(entry.getKey());
            }
        }
        if (beanNames.size() == 1) {
            return getBean(beanNames.get(0), requiredType);
        }

        throw new BeansException(requiredType + " expected single bean but found " + beanNames.size() + ": " + beanNames);
    }

}

