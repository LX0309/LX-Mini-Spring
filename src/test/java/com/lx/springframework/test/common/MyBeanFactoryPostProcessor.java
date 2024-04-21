package com.lx.springframework.test.common;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.PropertyValue;
import com.lx.springframework.beans.PropertyValues;
import com.lx.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.lx.springframework.beans.factory.config.BeanDefinition;
import com.lx.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}
