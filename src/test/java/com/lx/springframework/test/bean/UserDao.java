package com.lx.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    /**
     * 简单功能测试测试需要
     */
//    static {
//        hashMap.put("0309", "遇事不决Debug");
//        hashMap.put("1141", "以无止之神");
//        hashMap.put("7625", "行有止之境");
//    }
    public void initDataMethod() {
        System.out.println("执行：init-method");
        hashMap.put("0309", "遇事不决Debug");
        hashMap.put("1141", "以无止之神");
        hashMap.put("7625", "行有止之境");
    }
    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
