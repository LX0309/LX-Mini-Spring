package com.lx.springframework.test;

import com.lx.springframework.aop.AdvisedSupport;
import com.lx.springframework.aop.TargetSource;
import com.lx.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.lx.springframework.aop.framework.JdkDynamicAopProxy;
import com.lx.springframework.test.aopBean.IUserService;
import com.lx.springframework.test.aopBean.UserService;
import com.lx.springframework.test.aopBean.UserServiceInterceptor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AopApiTest {

    /**
     * 验证拦截的方法与对应的对象是否匹配
     * @throws NoSuchMethodException
     */
    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.lx.springframework.test.bean.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");
        System.out.println(clazz);
        System.out.println(method);
        System.out.println(pointcut.matches(clazz));        // true
        System.out.println(pointcut.matches(method, clazz));// true
    }

    @Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.lx.springframework.test.aopBean.IUserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.register("花花"));
    }

}
