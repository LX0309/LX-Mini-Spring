package com.lx.springframework.test;

import com.lx.springframework.context.support.ClassPathXmlApplicationContext;
import com.lx.springframework.test.Dependency_loopBean.Husband;
import com.lx.springframework.test.Dependency_loopBean.Wife;
import org.junit.Test;

/**
 * 依赖循环测试
 */
public class LoopApiTest {
    @Test
    public void test_circular() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springLoop.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        System.out.println("老公的媳妇：" + husband.queryWife());
        System.out.println("媳妇的老公：" + wife.queryHusband());
    }

}
