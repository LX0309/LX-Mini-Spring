package com.lx.springframework.aop;

/**
 * 目标源类，用于提供目标对象和目标类型信息。
 *
 * 作者：遇事不决DuBug   https://github.com/LX0309/LX-Mini-Spring
 */
public class TargetSource {

    private final Object target;

    /**
     * 构造函数，初始化目标对象。
     *
     * @param target 目标对象
     */
    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * 可能返回null，尽管某些情况下可以预先确定目标类。
     *
     * @return 此 {@link TargetSource} 返回的目标对象的类型
     */
    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    /**
     * 返回目标实例。在 AOP 框架调用 AOP 方法调用的目标之前立即调用此方法。
     *
     * @return 包含连接点的目标对象
     */
    public Object getTarget() {
        return this.target;
    }

}
