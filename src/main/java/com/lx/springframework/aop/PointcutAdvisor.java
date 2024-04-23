package com.lx.springframework.aop;

/**
 * 切点顾问接口，扩展自 {@link Advisor} 接口。
 * 用于获取驱动此顾问的切点。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * 获取驱动此顾问的切点。
     *
     * @return 切点对象
     */
    Pointcut getPointcut();

}
