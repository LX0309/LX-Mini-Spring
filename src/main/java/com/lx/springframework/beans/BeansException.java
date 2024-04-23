package com.lx.springframework.beans;

/**
 * 自定义运行时异常类
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class BeansException extends RuntimeException {

    /**
     * 使用指定的详细消息构造一个新的 BeansException。
     * @param msg 异常的详细消息。
     */
    public BeansException(String msg) {
        super(msg);
    }

    /**
     * 使用指定的详细消息和原始的 Throwable 原因构造一个新的 BeansException。
     * 这允许在 Spring 框架的底层捕获到原始异常时，将其封装在 BeansException 中抛出。
     *
     * @param msg    异常的详细消息。
     * @param cause  原始的 Throwable 原因，导致抛出此异常的根本原因。
     */
    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}