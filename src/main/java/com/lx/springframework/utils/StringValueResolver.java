package com.lx.springframework.utils;

/**
 * StringValueResolver 接口定义了解析字符串值的方法。
 * 实现该接口的类可以根据给定的字符串值进行解析，并返回解析后的结果。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface StringValueResolver {

    /**
     * 解析给定的字符串值。
     *
     * @param strVal 字符串值
     * @return 解析后的结果
     */
    String resolveStringValue(String strVal);

}
