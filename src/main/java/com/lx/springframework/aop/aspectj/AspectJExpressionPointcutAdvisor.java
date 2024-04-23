package com.lx.springframework.aop.aspectj;

import com.lx.springframework.aop.Pointcut;
import com.lx.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * AspectJExpressionPointcutAdvisor 类是 PointcutAdvisor 接口的实现，用于定义基于 AspectJ 表达式的切面。
 * 它包含了一个 AspectJExpressionPointcut 对象、一个 Advice 对象以及一个 AspectJ 表达式字符串。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    /**
     * 设置 AspectJ 表达式字符串。
     * @param expression AspectJ 表达式字符串
     */
    public void setExpression(String expression){
        this.expression = expression;
    }

    /**
     * 获取切点对象。
     * 如果切点对象为 null，则根据表达式字符串创建一个新的 AspectJExpressionPointcut 对象。
     * @return 切点对象
     */
    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    /**
     * 获取通知对象。
     * @return 通知对象
     */
    @Override
    public Advice getAdvice() {
        return advice;
    }

    /**
     * 设置通知对象。
     * @param advice 通知对象
     */
    public void setAdvice(Advice advice){
        this.advice = advice;
    }

}