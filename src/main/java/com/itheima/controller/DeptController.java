package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    // 日志记录对象 被注解@Slf4j代替
    //private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    /**
     * 查询全部部门数据
     *
     * @return
     */
    //@RequestMapping(value = "/depts" , method = RequestMethod.GET) // 指定请求方式为get
    @GetMapping
    public Result list() {
        log.info("list-查询全部部门数据");
        // 调用service查询部门数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /**
     * 按id删除部门
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteDeptById(@PathVariable Integer id) {
        log.info("根据id删除部门：{}", id);
        // 调用service删除部门
        deptService.deleteDeptById(id);
        return Result.success(); // 删除操作，不需要给页面返回data，给data传null即可
    }

    /**
     * 新增部门
     * @param dept
     * @return
     */
    @PostMapping
    public Result addDept(@RequestBody Dept dept) { // @ReposeBody: json string to Dept instance
        log.info("新增部门:{}", dept);
        // 调用service新增部门
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getDeptById(@PathVariable Integer id) { // 根据id查询，回显部门
        log.info("根据id:{}查询，回显部门", id);
        Dept dept = deptService.getDeptById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result updateDept(@RequestBody Dept dept) { // 修改部门
        log.info("修改部门信息，部门id:{}",dept.getId());
        log.info("要修改的部门信息:" + dept.toString());
        deptService.updateDept(dept);
        // 修改部门信息
        return Result.success();
    }
}
