package com.lx.springframework.core.io;

public interface ResourceLoader {

    /**
     * 加载类路径资源的伪 URL 前缀："classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}

