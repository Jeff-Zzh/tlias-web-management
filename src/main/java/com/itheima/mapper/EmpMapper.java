package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数
     *
     * @return
     */
    @Select("select count(*) from emp")
    public Long count();

    @Select("select * from emp limit #{start}, #{pageSize}")
    public List<Emp> page(@Param("start") Integer start, @Param("pageSize") Integer pageSize); // 起始下标 每页展示的行数

    /**
     * 员工信息查询，搭配pagehelper
     *
     * @return
     */
//    @Select("select * from emp")
    public List<Emp> list(@Param("name") String name, @Param("gender") Short gender, @Param("begin") LocalDate begin, @Param("end") LocalDate end);

    /**
     * sql按id批量删除
     *
     * @param ids
     */
    void deleteEmpByIds(@Param("ids") List<Integer> ids);

    /**
     * 新增员工
     *
     * @param emp
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) VALUES " +
            "(#{username}, #{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void addEmp(Emp emp);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @Select("select * from emp where id = #{id}")
    Emp getEmpById(Integer id);

    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 根据username和password查询员工登录信息
     * @param emp
     * @return
     */
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getEmpByUsernameAndPassword(Emp emp);

    /**
     * 根据部门id删除某部门下的所有员工
     * @param deptId
     */
    @Delete("delete from emp where dept_id=#{deptId}")
    void deleteByDeptId(Integer deptId);
}
