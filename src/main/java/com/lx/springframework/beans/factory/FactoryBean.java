package com.lx.springframework.beans.factory;

/**
 * 创建和操纵对象的接口
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface FactoryBean<T> {

    /**
     * 返回由 FactoryBean 创建的对象实例。
     * 此方法由 Spring 容器调用以获取 Bean 实例。
     * 如果需要返回一个具体类型的对象，可以定义泛型参数 T。
     *
     * @return 由 FactoryBean 创建的对象实例
     * @throws Exception 如果在创建对象过程中发生任何异常，则抛出异常
     */
    T getObject() throws Exception;

    /**
     * 指定由 FactoryBean 创建的对象类型。
     * 此方法返回的对象类型信息可以用于类型检查和自动装配。
     * 如果 FactoryBean 创建的对象类型可能是多种类型之一，可以返回 Object.class。
     *
     * @return 由 FactoryBean 创建的对象类型
     */
    Class<?> getObjectType();

    /**
     * 指示由 FactoryBean 创建的对象是单例还是原型。
     * 如果 FactoryBean 创建的实例应该是单例（在 Spring 容器中只存在一个共享实例），则返回 true。
     * 如果每次请求时都应该创建一个新的实例，则返回 false。
     *
     * @return 是否为单例（true 表示单例，false 表示原型）
     */
    boolean isSingleton();

}
