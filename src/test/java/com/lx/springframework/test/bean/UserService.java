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

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }
    public void queryUserInfoDao() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder("");
        str.append("").append(name);
        return str.toString();
    }
}
