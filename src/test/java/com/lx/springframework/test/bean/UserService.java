package com.lx.springframework.test.bean;

public class UserService {

    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder("");
        str.append("").append(name);
        return str.toString();
    }
}
