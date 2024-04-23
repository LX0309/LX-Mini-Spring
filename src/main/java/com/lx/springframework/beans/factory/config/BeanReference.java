package com.lx.springframework.beans.factory.config;

/**
 * BeanReference 类用于表示对另一个 Bean 的引用。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class BeanReference {

    /** 引用的 Bean 的名称 */
    private final String beanName;

    /**
     * 构造一个 BeanReference 对象，指定引用的 Bean 的名称。
     *
     * @param beanName 引用的 Bean 的名称
     */
    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 获取引用的 Bean 的名称。
     *
     * @return 引用的 Bean 的名称
     */
    public String getBeanName() {
        return beanName;
    }

}