package com.lx.springframework.beans.factory;



public interface DisposableBean {

    void destroy() throws Exception;

}