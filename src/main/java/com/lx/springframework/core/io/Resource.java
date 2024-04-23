package com.lx.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource 接口定义了获取资源输入流的方法。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface Resource {

    /**
     * 获取资源的输入流。
     *
     * @return 资源的输入流
     * @throws IOException 如果无法获取输入流
     */
    InputStream getInputStream() throws IOException;
}

