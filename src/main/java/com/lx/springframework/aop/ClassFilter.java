package com.lx.springframework.aop;

/**
 * 定义类匹配类，用于切点找到给定的接口和目标类。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface ClassFilter {

    /**
     * 判断切入点是否适用于给定的接口或目标类。
     * @param clazz 候选目标类
     * @return 通知是否应该应用于给定的目标类
     */
    boolean matches(Class<?> clazz);

}
