package com.lx.springframework.beans.factory;

import com.lx.springframework.beans.BeansException;


public interface BeanFactory {
    Object getBean(String name) throws BeansException;


}
