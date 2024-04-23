package com.lx.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * UrlResource 类实现了 Resource 接口，用于表示 URL 资源。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class UrlResource implements Resource {

    private final URL url; // URL 对象

    /**
     * 构造函数，使用指定的 URL 创建 UrlResource 实例。
     *
     * @param url URL 对象
     * @throws IllegalArgumentException 如果 URL 为空
     */
    public UrlResource(URL url) {
        Assert.notNull(url, "URL必须不为空！"); // 断言URL不为空
        this.url = url;
    }

    /**
     * 获取资源的输入流。
     *
     * @return 资源的输入流
     * @throws IOException 如果无法获取输入流
     */
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection(); // 打开连接
        try {
            return con.getInputStream(); // 获取输入流
        } catch (IOException ex) {
            if (con instanceof HttpURLConnection) { // 如果是 HTTP 连接
                ((HttpURLConnection) con).disconnect(); // 断开连接
            }
            throw ex; // 抛出异常
        }
    }
}
