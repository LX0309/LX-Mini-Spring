package com.lx.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.lx.springframework.beans.factory.config.BeanDefinition;
import com.lx.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 提供了在类路径中扫描特定注解的组件的功能，并将匹配的类封装为 BeanDefinition 对象的集合
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 在给定的基包路径下扫描所有带有 @Component 注解的类，并返回它们的 BeanDefinition 集合。
     * 这个方法使用了 ClassUtil.scanPackageByAnnotation 工具方法来执行实际的类扫描工作。
     *
     * @param basePackage 要扫描的包名
     * @return BeanDefinition 集合，包含了扫描到的所有候选组件的类定义
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>(); // 使用 LinkedHashSet 保持添加顺序
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        // 遍历所有扫描到的类，并为每个类创建一个 BeanDefinition 对象
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates; // 返回包含所有 Bean 定义的集合
    }

}
