package com.lx.springframework.core.io;

/**
 * ResourceLoader 接口定义了加载资源的方法。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface ResourceLoader {

    /**
     * 加载类路径资源的伪 URL 前缀："classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 根据给定的位置加载资源。
     *
     * @param location 资源位置
     * @return 资源对象
     */
    Resource getResource(String location);

}

