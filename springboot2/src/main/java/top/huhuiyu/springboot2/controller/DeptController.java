package top.huhuiyu.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.entity.TbDept;
import top.huhuiyu.springboot2.service.DeptService;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/query")
    public BaseResult<List<TbDept>> query() throws Exception {
        return deptService.query();
    }

    @PostMapping("/add")
    public BaseResult<TbDept> add(TbDept dept) throws Exception {
        return deptService.add(dept);
    }

    @PostMapping("/update")
    public BaseResult<TbDept> update(TbDept dept) throws Exception {
        return deptService.update(dept);
    }

    @PostMapping("/delete")
    public BaseResult<TbDept> delete(TbDept dept) throws Exception {
        return deptService.delete(dept);
    }
}
