package com.itheima;


import com.itheima.controller.DeptController;
import com.itheima.pojo.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class getBeanMannually {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
     public void test(){
        // 根据bean名称 获取bean
        DeptController bean1 = (DeptController) applicationContext.getBean("deptController");// bean名称默认是类名首字母小写
        System.out.println(bean1);

        // 根据bean类型获取bean
        DeptController bean2 = applicationContext.getBean(DeptController.class);
        System.out.println(bean2);

        // 根据bean类型和名称获取
        DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println(bean3);
    }
}
