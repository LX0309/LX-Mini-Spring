package com.lx.springframework.beans.factory.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.FactoryBean;
import com.lx.springframework.beans.factory.config.BeanDefinition;
import com.lx.springframework.beans.factory.config.BeanPostProcessor;
import com.lx.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.lx.springframework.utils.ClassUtils;
import com.lx.springframework.utils.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的 Bean 工厂，实现了 ConfigurableBeanFactory 接口。
 * 提供了 Bean 工厂的基本结构和功能。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    /** 用于解析 bean 类名的类加载器 */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /** Bean 后置处理器列表 */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /** 用于解析嵌入式值的字符串解析器列表 */
    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    /**
     * 根据给定的 bean 名称获取相应的 bean 实例。
     *
     * @param name bean 名称
     * @return bean 实例
     * @throws BeansException 如果无法获取 bean 实例
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    /**
     * 根据给定的 bean 名称和参数获取相应的 bean 实例。
     *
     * @param name bean 名称
     * @param args 构造函数参数
     * @return bean 实例
     * @throws BeansException 如果无法获取 bean 实例
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    /**
     * 根据给定的 bean 名称和类型获取相应的 bean 实例。
     *
     * @param name         bean 名称
     * @param requiredType bean 类型
     * @return bean 实例
     * @throws BeansException 如果无法获取 bean 实例
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    /**
     * 根据给定的 bean 名称和参数获取相应的 bean 实例。
     *
     * @param name bean 名称
     * @param args 构造函数参数
     * @return bean 实例
     */
    protected <T> T doGetBean(final String name, final Object[] args) {
        // 检查是否有单例实例
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        // 获取 BeanDefinition
        BeanDefinition beanDefinition = getBeanDefinition(name);
        // 创建 bean 实例
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    /**
     * 根据给定的 bean 实例和名称获取相应的对象。
     *
     * @param beanInstance bean 实例
     * @param beanName     bean 名称
     * @return 对象实例
     */
    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);

        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }

    /**
     * 根据给定的 bean 名称获取相应的 BeanDefinition。
     *
     * @param beanName bean 名称
     * @return 对应的 BeanDefinition
     * @throws BeansException 如果无法获取 BeanDefinition
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 根据给定的 bean 名称、BeanDefinition 和参数创建相应的 bean 实例。
     *
     * @param beanName       bean 名称
     * @param beanDefinition BeanDefinition
     * @param args           构造函数参数
     * @return bean 实例
     * @throws BeansException 如果无法创建 bean 实例
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    /**
     * 向工厂添加 Bean 后置处理器。
     *
     * @param beanPostProcessor Bean 后置处理器
     */
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 添加字符串解析器，用于解析嵌入式值。
     *
     * @param valueResolver 字符串解析器
     */
    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    /**
     * 解析嵌入式值。
     *
     * @param value 原始值
     * @return 解析后的值
     */
    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    /**
     * 返回应用于该工厂创建的 bean 的 Bean 后置处理器列表。
     *
     * @return Bean 后置处理器列表
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    /**
     * 返回用于解析 bean 类名的类加载器。
     *
     * @return 类加载器
     */
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

}

