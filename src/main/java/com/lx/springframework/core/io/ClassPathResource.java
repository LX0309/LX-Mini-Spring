package com.lx.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.lx.springframework.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassPathResource 类实现了 Resource 接口，用于表示类路径下的资源。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class ClassPathResource implements Resource {

    private final String path; // 资源路径

    private ClassLoader classLoader; // 类加载器

    /**
     * 构造函数，创建一个指定路径的 ClassPathResource 实例。
     *
     * @param path 资源路径
     */
    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    /**
     * 构造函数，创建一个指定路径和类加载器的 ClassPathResource 实例。
     *
     * @param path        资源路径
     * @param classLoader 类加载器
     */
    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null"); // 断言路径不为空
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader()); // 如果类加载器不为空则使用指定的类加载器，否则使用默认类加载器
    }

    /**
     * 获取资源的输入流。
     *
     * @return 资源的输入流
     * @throws IOException 如果无法获取输入流或资源不存在
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path); // 使用类加载器获取资源的输入流
        if (is == null) {
            throw new FileNotFoundException(
                    this.path + " cannot be opened because it does not exist"); // 如果输入流为空，则抛出文件未找到异常
        }
        return is;
    }
}



