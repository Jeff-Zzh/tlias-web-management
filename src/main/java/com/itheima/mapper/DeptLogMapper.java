package com.itheima.mapper;

import com.itheima.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface DeptLogMapper {

    @Insert("insert into dept_log(creaate_time, description) VALUES (#{createTime}, #{description})")
    void insertDeptLog(DeptLog deptLog);
}
