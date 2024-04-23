package com.lx.springframework.utils;

/**
 * ClassUtils 类提供了一些与类操作相关的静态方法。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class ClassUtils {

    /**
     * 获取默认的类加载器。
     *
     * @return 默认的类加载器
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader(); // 获取当前线程的上下文类加载器
        } catch (Throwable ex) {
            // 无法访问线程上下文类加载器 - 返回系统类加载器...
        }
        if (cl == null) {
            // 没有线程上下文类加载器 -> 使用该类的类加载器
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    /**
     * 检查指定的类是否为 CGLIB 生成的代理类。
     *
     * @param clazz 要检查的类
     * @return 如果是 CGLIB 生成的代理类，则返回 true；否则返回 false
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    /**
     * 检查指定的类名是否为 CGLIB 生成的代理类。
     *
     * @param className 要检查的类名
     * @return 如果是 CGLIB 生成的代理类，则返回 true；否则返回 false
     */
    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$")); // 检查类名是否包含 "$$"
    }
}
