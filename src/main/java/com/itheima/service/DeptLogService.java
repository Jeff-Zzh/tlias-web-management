package com.itheima.service;

import com.itheima.pojo.DeptLog;

public interface DeptLogService {
    /**
     * 新增部门操作日志
     * @param deptLog
     */
    public void insert(DeptLog deptLog);
}
