package com.lx.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * 获取切面通知部分的方法
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface Advisor {

    /**
     * 返回此切面的通知部分。通知可以是拦截器、前置通知、异常通知等。
     * @return 如果切入点匹配，则应该应用的通知
     * @see org.aopalliance.intercept.MethodInterceptor
     * @see BeforeAdvice
     */
    Advice getAdvice();

}
