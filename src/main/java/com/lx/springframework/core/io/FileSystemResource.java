package com.lx.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FileSystemResource 类实现了 Resource 接口，用于表示文件系统中的资源。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class FileSystemResource implements Resource {

    private final File file; // 文件对象
    private final String path; // 资源路径

    /**
     * 构造函数，使用指定的文件创建 FileSystemResource 实例。
     *
     * @param file 文件对象
     */
    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    /**
     * 构造函数，使用指定的路径创建 FileSystemResource 实例。
     *
     * @param path 资源路径
     */
    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    /**
     * 获取资源的输入流。
     *
     * @return 资源的输入流
     * @throws IOException 如果无法获取输入流
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file); // 返回文件的输入流
    }

    /**
     * 获取资源的路径。
     *
     * @return 资源的路径
     */
    public final String getPath() {
        return this.path; // 返回资源的路径
    }
}
