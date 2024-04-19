package com.lx.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 通过 HTTP 的方式读取云服务的文件（如GitHub 或者 Gitee 上的配置文件）
 */
public class UrlResource implements Resource{

    private final URL url;

    public UrlResource(URL url){
        Assert.notNull(url,"URL必须不为空！");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try{
            return con.getInputStream();
        }catch (IOException ex){
            if(con instanceof HttpURLConnection){
                ((HttpURLConnection) con).disconnect();
            }
            throw ex;
        }
    }
}
