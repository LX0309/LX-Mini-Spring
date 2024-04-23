package com.lx.springframework.aop.aspectj;

import com.lx.springframework.aop.ClassFilter;
import com.lx.springframework.aop.MethodMatcher;
import com.lx.springframework.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 定义和解析切入点表达式
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

    // 定义支持的切入点原语集合，目前仅添加了 EXECUTION
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    // 静态代码块，初始化支持的原语
    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    // 表示解析后的切入点表达式
    private final PointcutExpression pointcutExpression;

    /*
     * 构造函数，接收一个字符串表达式，然后解析它为 PointcutExpression 对象。
     * 这里使用了 PointcutParser 来解析表达式，并且指定了支持的原语和类加载器。
     */
    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    // 实现 ClassFilter 接口的 matches 方法，判断类是否匹配切入点
    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    // 实现 MethodMatcher 接口的 matches 方法，判断方法是否匹配切入点
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        // 这里使用 alwaysMatches() 表示匹配所有方法，实际使用时可能需要根据具体情况进行判断
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    // 实现 Pointcut 接口的 getClassFilter 方法，返回关联的 ClassFilter 对象
    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    // 实现 Pointcut 接口的 getMethodMatcher 方法，返回关联的 MethodMatcher 对象
    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

}