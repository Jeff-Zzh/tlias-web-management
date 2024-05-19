package com.itheima.service.impl;

import com.itheima.aop.MyLog;
import com.itheima.mapper.DeptLogMapper;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @MyLog
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @MyLog
    @Transactional(rollbackFor = Exception.class) // Spring事务管理，出现任何异常，都会回滚
    @Override
    public void deleteDeptById(Integer id) {
        try {
            deptMapper.deleteById(id); // 根据部门id删除部门数据
//            int i = 1/0; Arthmetic Exception
            empMapper.deleteByDeptId(id); // 根据部门id删除该部门下的员工
        } finally { // 删除部门时，无论删除成功还是失败，都要记录操作日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了删除部门的操作，删除" + id + "号部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        // 新部门信息补全
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 调mapper层操作DB
        deptMapper.insert(dept);
    }

    @Override
    public Dept getDeptById(Integer id) {
        return deptMapper.getDeptById(id);
    }

    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDeptById(dept);
    }
}
