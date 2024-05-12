package com.itheima.service;

import com.itheima.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    /**
     * 查询全部部门数据
     * @return
     */
    List<Dept> list();

    /**
     * 根据id删除部门
     */
    void deleteDeptById(Integer id);

    /**
     * 添加部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 根据id查询部门，回显
     * @param id
     * @return
     */
    Dept getDeptById(Integer id);

    /**
     * 修改部门信息
     * @param dept
     */
    void updateDept(Dept dept);
}
