package com.lx.springframework.beans.factory;

import com.lx.springframework.beans.BeansException;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}