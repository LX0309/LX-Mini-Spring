package com.lx.springframework.test;


import com.lx.springframework.beans.factory.BeanFactory;
import com.lx.springframework.beans.factory.config.BeanDefinition;
import com.lx.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    /**
     * 简单Bean容器测试
     */
    @Test
    public void test_BeanFactory(){
        //1.初始化BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        //2.注册Bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition(UserService.class.getName(),beanDefinition);

        //3。获取Bean(不去new userService)
        UserService userService = (UserService) beanFactory.getBean(UserService.class.getName());
        userService.queryUserInfo();
    }

}
