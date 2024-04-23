package com.lx.springframework.context.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.config.BeanPostProcessor;
import com.lx.springframework.context.ApplicationContext;

/**
 * ApplicationContextAwareProcessor 类实现了 BeanPostProcessor 接口。
 * 它用于在 Bean 初始化前后对实现了 ApplicationContextAware 接口的 Bean 进行处理，
 * 将应用程序上下文注入到这些 Bean 中。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    /**
     * 构造函数，接收一个 ApplicationContext 对象作为参数。
     *
     * @param applicationContext 应用程序上下文对象
     */
    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 在 Bean 初始化前进行处理。
     *
     * @param bean     正在初始化的 Bean 对象
     * @param beanName Bean 的名称
     * @return 处理后的 Bean 对象
     * @throws BeansException 如果处理过程中发生异常
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 如果 Bean 实现了 ApplicationContextAware 接口，将应用程序上下文注入到 Bean 中
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    /**
     * 在 Bean 初始化后进行处理，此处不对 Bean 进行额外操作，直接返回原始 Bean 对象。
     *
     * @param bean     已经初始化的 Bean 对象
     * @param beanName Bean 的名称
     * @return 处理后的 Bean 对象
     * @throws BeansException 如果处理过程中发生异常
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
