package com.lx.springframework.beans.factory;


/**
 * 回调接口，用于在容器关闭时，显式地执行 Bean 的销毁逻辑
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface DisposableBean {

    /**
     * 销毁 Bean 实例时由 Spring 容器调用的方法。
     * 实现此接口的 Bean 可以提供一个自定义的销毁方法，例如释放资源或清理。
     * 此方法通常由 IoC 容器在容器关闭时调用，用于执行清理逻辑。
     *
     * @throws Exception 如果销毁过程中发生任何异常，实现类可以声明并抛出异常，
     *                   这将被 Spring 容器捕获并处理。
     */
    void destroy() throws Exception;

}