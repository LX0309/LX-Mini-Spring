package com.lx.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获取InputStream流方法
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
