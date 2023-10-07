package top.huhuiyu.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.springboot2.base.BasePageResult;
import top.huhuiyu.springboot2.entity.PageBean;
import top.huhuiyu.springboot2.entity.TbEmployee;
import top.huhuiyu.springboot2.service.TbEmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    // 不通过@Autowired引入依赖，就最好是构造传入，被依赖的字段用final修饰
    private final TbEmployeeService tbEmployeeService;

    public EmployeeController(TbEmployeeService tbEmployeeService) {
        this.tbEmployeeService = tbEmployeeService;
    }

    @GetMapping("/query")
    public BasePageResult<List<TbEmployee>> query(TbEmployee employee, PageBean page) throws Exception {
        return tbEmployeeService.query(employee, page);
    }


}
