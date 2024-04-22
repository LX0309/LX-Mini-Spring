package com.lx.springframework.context.support;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.beans.factory.Aware;
import com.lx.springframework.context.ApplicationContext;

/**
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
