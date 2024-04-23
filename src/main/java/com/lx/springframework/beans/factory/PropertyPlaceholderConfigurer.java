package com.lx.springframework.beans.factory;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.PropertyValue;
import com.lx.springframework.beans.PropertyValues;
import com.lx.springframework.beans.factory.config.BeanDefinition;
import com.lx.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.lx.springframework.core.io.DefaultResourceLoader;
import com.lx.springframework.core.io.Resource;
import com.lx.springframework.utils.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * 对配置文件的加载以及摘取占位符中的在属性文件里的配置
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * 默认的占位符前缀：${}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * 默认的占位符后缀：}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    // 属性文件的位置
    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            // 使用资源加载器加载属性文件
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            // 加载属性值
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            // 遍历所有 Bean 定义，替换属性值中的占位符
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (value instanceof String) {
                        // 替换属性值中的占位符
                        value = resolvePlaceholder((String) value, properties);
                        // 更新 Bean 定义中的属性值
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
                    }
                }
            }

            // 添加字符串解析器，用于解析 @Value 注解
            StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(valueResolver);

        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    /**
     * 解析占位符并用属性值替换。
     */
    private String resolvePlaceholder(String value, Properties properties) {
        String strVal = value;
        StringBuilder buffer = new StringBuilder(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIdx, stopIdx + 1, propVal);
        }
        return buffer.toString();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 内部类，用于解析字符串中的占位符。
     */
    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
        }

    }

}
