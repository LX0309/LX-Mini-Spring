package com.lx.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.lx.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.lx.springframework.beans.factory.config.BeanDefinition;
import com.lx.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.lx.springframework.stereotype.Component;

import java.util.Set;

/**
 * 负责在类路径中扫描特定的注解，并将匹配的类定义为 BeanDefinition，注册到 BeanDefinitionRegistry 中
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry registry; // 用于注册 Bean 定义的注册表

    /**
     * 构造函数，传入 BeanDefinitionRegistry 实例。
     */
    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 执行扫描并注册找到的 Bean 定义。
     * 扫描指定的基包路径，查找候选的 Bean 组件，并注册它们。
     */
    public void doScan(String... basePackages) {
        // 遍历所有基包路径
        for (String basePackage : basePackages) {
            // 查找当前包下的所有候选组件
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            // 遍历所有候选组件，并注册它们
            for (BeanDefinition beanDefinition : candidates) {
                // 解析 Bean 的作用域
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope); // 设置 Bean 定义的作用域
                }
                // 注册 Bean 定义
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }

        // 注册处理特定注解的 BeanPostProcessor，如 @Autowired 和 @Value
        registry.registerBeanDefinition("com.lx.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor",
                new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    /**
     * 解析 Bean 定义的作用域。
     */
    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class); // 获取作用域注解
        if (null != scope) {
            return scope.value(); // 返回注解中指定的作用域
        }
        return StrUtil.EMPTY; // 默认为空字符串，表示使用 Spring 的默认作用域
    }

    /**
     * 确定 Bean 的名称。
     * 从 @Component 或其复合注解（@Controller, @Service 等）中获取名称，
     * 如果没有指定名称，则使用类名的非大写形式。
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class); // 获取 @Component 注解
        String value = component.value(); // 获取注解中的 value 属性
        if (StrUtil.isEmpty(value)) { // 如果 value 为空
            value = StrUtil.lowerFirst(beanClass.getSimpleName()); // 使用类名的非大写形式
        }
        return value; // 返回确定的 Bean 名称
    }

}
