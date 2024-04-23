package com.lx.springframework.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配，找到表达式范围内匹配下的目标类和方法。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface MethodMatcher {

    /**
     * 执行静态检查，判断给定的方法是否匹配。
     * @param method 要匹配的方法
     * @param targetClass 目标类
     * @return 是否静态匹配
     */
    boolean matches(Method method, Class<?> targetClass);

}
