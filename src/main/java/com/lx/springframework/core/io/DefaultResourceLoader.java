package com.lx.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * DefaultResourceLoader 类实现了 ResourceLoader 接口，用于根据给定的位置加载资源。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class DefaultResourceLoader implements ResourceLoader {

    /**
     * 根据给定的位置加载资源。
     *
     * @param location 资源位置
     * @return 资源对象
     */
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null"); // 断言位置不为空
        if (location.startsWith(CLASSPATH_URL_PREFIX)) { // 如果位置以"classpath:"开头
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length())); // 创建 ClassPathResource 实例
        }
        else { // 否则
            try {
                URL url = new URL(location); // 尝试将位置解析为 URL
                return new UrlResource(url); // 创建 UrlResource 实例
            } catch (MalformedURLException e) { // 如果解析为 URL 失败
                return new FileSystemResource(location); // 创建 FileSystemResource 实例
            }
        }
    }

}


