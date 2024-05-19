package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page, // 分页查询页码
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {// 分页查询每页记录数

        log.info("分页查询，参数:{},{},{},{},{},{}", page, pageSize, name, gender,begin,end);
        // 调用service分页查询 响应结果:总记录数、结果列表
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result deleteEmpById(@PathVariable List<Integer> ids){
        log.info("批量删除操作，ids:{}",ids);
        empService.deleteEmpByIds(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result addEmp(@RequestBody Emp emp){
        log.info("新增员工：{}", emp);
        empService.addEmp(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询员工信息，id：{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息：{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
