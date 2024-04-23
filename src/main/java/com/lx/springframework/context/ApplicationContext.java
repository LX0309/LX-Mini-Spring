package com.lx.springframework.context;

import com.lx.springframework.beans.factory.ListableBeanFactory;

/**
 * ApplicationContext 接口代表了 Spring 应用程序上下文，它继承自 ListableBeanFactory 接口，
 * 提供了访问应用程序组件的功能，包括 Bean 的获取、依赖注入、生命周期管理等。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public interface ApplicationContext extends ListableBeanFactory {

}
