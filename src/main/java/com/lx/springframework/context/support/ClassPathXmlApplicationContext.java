package com.lx.springframework.context.support;

import com.lx.springframework.beans.BeansException;

/**
 * ClassPathXmlApplicationContext 类继承自 AbstractXmlApplicationContext，用于从 XML 文件中加载 BeanDefinition 并刷新应用程序上下文。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    /**
     * 默认构造函数。
     */
    public ClassPathXmlApplicationContext() {
    }

    /**
     * 构造函数，从指定的 XML 文件中加载 BeanDefinition 并刷新上下文。
     *
     * @param configLocations XML 文件路径
     * @throws BeansException 如果加载或刷新过程中发生异常
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    /**
     * 构造函数，从指定的多个 XML 文件中加载 BeanDefinition 并刷新上下文。
     *
     * @param configLocations 多个 XML 文件路径
     * @throws BeansException 如果加载或刷新过程中发生异常
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    /**
     * 获取配置位置数组。
     *
     * @return 配置位置数组
     */
    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }

}