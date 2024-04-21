package com.lx.springframework.test;


import cn.hutool.core.io.IoUtil;
import com.lx.springframework.beans.PropertyValue;
import com.lx.springframework.beans.PropertyValues;
import com.lx.springframework.beans.factory.config.BeanDefinition;
import com.lx.springframework.beans.factory.config.BeanReference;
import com.lx.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.lx.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.lx.springframework.context.support.ClassPathXmlApplicationContext;
import com.lx.springframework.core.io.DefaultResourceLoader;
import com.lx.springframework.core.io.Resource;
import com.lx.springframework.test.bean.UserDao;
import com.lx.springframework.test.bean.UserService;


import com.lx.springframework.test.common.MyBeanFactoryPostProcessor;
import com.lx.springframework.test.common.MyBeanPostProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

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
     *基于JDK实现含构造函数的类实例化测试
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

    /**
     * Bean注入属性（在类实例化创建之后进行属性填充）以及依赖对象实现测试
     */
    @Test
    public void test_BeanFactory3() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "1141"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        String result = userService.queryUserInfoDao();
        System.out.println("测试结果：" + result);
    }

    /**
     * 资源加载测试
     */
    private DefaultResourceLoader resourceLoader;

    /**
     * //@BeforeEach JUnit 5测试前初始化方法
     */
    @BeforeEach
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    /**
     *名称访问配置文件
     * @throws IOException
     */
    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    /**
     * 全路径仿配置文件
     * @throws IOException
     */
    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    /**
     * 阿里云远程访问配置文件
     * @throws IOException
     */
    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://lx-web-talis.oss-cn-guangzhou.aliyuncs.com/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    /**
     * 读取配置文件&注册Bean
     */
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("src/test/resources/spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfoDao();
        System.out.println("测试结果：" + result);
    }

    /**
     * 不应用上下文
     */
    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo1();
        System.out.println("测试结果：" + result);
    }

    /**
     * 应用上下文
     */
    @Test
    public void test_xml_1() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo1();
        System.out.println("测试结果：" + result);
    }

}
