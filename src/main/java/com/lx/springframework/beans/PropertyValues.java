package com.lx.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于表示一组 PropertyValue 对象的集合
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class PropertyValues {

    /**
     * 存储 PropertyValue 对象的列表，用于表示多个属性值。
     */
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 向属性值列表中添加一个新的 PropertyValue 对象。
     *
     * @param pv 要添加的 PropertyValue 对象
     */
    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    /**
     * 获取所有的 PropertyValue 对象作为数组。
     *
     * @return PropertyValue 对象数组
     */
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 根据属性名称获取特定的 PropertyValue 对象。
     *
     * @param propertyName 要查找的属性名称
     * @return 如果找到，则返回对应的 PropertyValue 对象；否则返回 null
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }

}

