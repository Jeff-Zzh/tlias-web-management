package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
//        // 1. 获取总记录数
//        Long count = empMapper.count();
//        // 2. 获取分页查询结果列表
//        List<Emp> empList = empMapper.page((page - 1) * pageSize, pageSize);
//
//        // 3.封装PageBean对象
//        PageBean pageBean = new PageBean(count, empList);
//        return pageBean;

        // 1.设置分页参数
        PageHelper.startPage(page, pageSize);
        // 2.执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;
        // 3.封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult()); // 总记录数 结果列表
        return pageBean;
    }

    @Override
    public void deleteEmpByIds(List<Integer> ids) {
        empMapper.deleteEmpByIds(ids);
    }

    @Override
    public void addEmp(Emp emp) {
        // 补充实体类基础信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getEmpById(id);
    }

    @Override
    public void update(Emp emp) {
        // 添加员工信息更新时间
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        Emp emp1 = empMapper.getEmpByUsernameAndPassword(emp);
        return emp1;
    }
}
