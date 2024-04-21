package com.lx.springframework.beans.factory;

import com.lx.springframework.beans.BeansException;
import com.lx.springframework.context.ApplicationContext;

/**
 * 实现此接口，既能感知到所属的 ApplicationContext
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
