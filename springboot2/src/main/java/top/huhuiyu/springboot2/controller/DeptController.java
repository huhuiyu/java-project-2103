package top.huhuiyu.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.springboot2.base.BasePageResult;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.entity.PageBean;
import top.huhuiyu.springboot2.entity.TbDept;
import top.huhuiyu.springboot2.service.DeptService;
import top.huhuiyu.springboot2.validate.DeptValidate;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/query")
    public BasePageResult<List<TbDept>> query(TbDept dept, PageBean page) throws Exception {
        return deptService.query(dept, page);
    }

    @PostMapping("/add")
    public BaseResult<TbDept> add(@Validated({DeptValidate.Add.class}) TbDept dept) throws Exception {
        return deptService.add(dept);
    }

    @PostMapping("/update")
    public BaseResult<TbDept> update(@Validated(DeptValidate.Update.class) TbDept dept) throws Exception {
        return deptService.update(dept);
    }

    @PostMapping("/delete")
    public BaseResult<TbDept> delete(TbDept dept) throws Exception {
        return deptService.delete(dept);
    }
}
