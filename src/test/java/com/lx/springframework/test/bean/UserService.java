package com.lx.springframework.test.bean;

public class UserService {

    private String name;

    private String uId;

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
}
