package com.lx.springframework.test.bean;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.*;
import com.lx.springframework.context.ApplicationContext;
import com.lx.springframework.context.support.ApplicationContextAware;

public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private String name;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;
    private String uId;
    private String company;
    private IUserDao IuserDao;

    public IUserDao getIuserDao() {
        return IuserDao;
    }

    public void setIuserDao(IUserDao iuserDao) {
        IuserDao = iuserDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;

    private UserDao userDao;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public String queryUserInfo() {
        return "查询用户信息：" + name;
    }
    public String queryUserInfoDao() {
        return "查询用户信息：" + userDao.queryUserName(uId);
    }

    public String queryUserInfo1() {
        return userDao.queryUserName(uId)+", 公司："+company+", 地点"+location;
    }
    public String queryIUserInfo() {
        return IuserDao.queryUserName(uId)+", 公司："+company+", 地点"+location;
    }
    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder("");
        str.append("").append(name);
        return str.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is：" + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader：" + classLoader);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }
}
