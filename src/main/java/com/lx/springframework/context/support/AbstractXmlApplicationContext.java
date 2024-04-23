package com.lx.springframework.context.support;

import com.lx.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.lx.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * AbstractXmlApplicationContext 是一个抽象类，继承自 AbstractRefreshableApplicationContext。
 * 它用于从 XML 文件中加载 Bean 定义，并提供了一个抽象方法用于获取配置文件的位置。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    /**
     * 加载 Bean 定义的具体实现方法。在这个方法中，使用 XmlBeanDefinitionReader 来读取 XML 配置文件，并加载 Bean 定义。
     *
     * @param beanFactory BeanFactory 实例
     */
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        // 创建 XmlBeanDefinitionReader 实例，传入 BeanFactory 和当前 ApplicationContext
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        // 获取配置文件的位置
        String[] configLocations = getConfigLocations();
        // 如果配置文件位置不为空，则加载配置文件中的 Bean 定义
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 获取配置文件的位置的抽象方法，由子类实现具体逻辑。
     *
     * @return 配置文件的位置数组
     */
    protected abstract String[] getConfigLocations();

}

