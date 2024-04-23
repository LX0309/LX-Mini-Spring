package com.lx.springframework.aop;

/**
 * 切点接口，用于定义类过滤器和方法匹配器。
 * 通过实现此接口，可以定义切入点，即在何处应用通知。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface Pointcut {

    /**
     * 返回此切点的类过滤器。
     *
     * @return 类过滤器（永远不会是 <code>null</code>）
     */
    ClassFilter getClassFilter();

    /**
     * 返回此切点的方法匹配器。
     *
     * @return 方法匹配器（永远不会是 <code>null</code>）
     */
    MethodMatcher getMethodMatcher();

}

