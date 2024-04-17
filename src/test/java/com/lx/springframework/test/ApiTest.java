package com.lx.springframework.test;


import com.lx.springframework.beans.factory.config.BeanDefinition;
import com.lx.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.lx.springframework.test.bean.UserService;

import org.junit.jupiter.api.Test;

public class ApiTest {

    /**
     * 简单Bean容器测试
     */
    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        System.out.println(beanFactory.getBean("userService"));
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getSingleton("userService");
        userService_singleton.queryUserInfo();
    }

    /**
     *基于Cglib实现含构造函数的类实例化测试
     */
    @Test
    public void test_BeanFactory2() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 3. 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 4.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "遇事不决DeBug");
        userService.queryUserInfo();
    }

}
