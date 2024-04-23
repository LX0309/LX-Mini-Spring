package com.lx.springframework.beans;

/**
 * 传递类中属性信息的类
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class PropertyValue {

    /**
     * 属性的名称。
     */
    private final String name;

    /**
     * 属性的值。
     */
    private final Object value;

    /**
     * 构造函数，用于创建一个新的 PropertyValue 实例。
     *
     * @param name  属性的名称
     * @param value 属性的值
     */
    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 获取属性的名称。
     *
     * @return 属性名称的字符串
     */
    public String getName() {
        return name;
    }

    /**
     * 获取属性的值。
     *
     * @return 属性值的对象
     */
    public Object getValue() {
        return value;
    }

}
