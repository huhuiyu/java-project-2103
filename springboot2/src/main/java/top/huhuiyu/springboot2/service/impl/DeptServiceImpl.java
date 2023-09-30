package top.huhuiyu.springboot2.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.dao.TbDeptDAO;
import top.huhuiyu.springboot2.entity.PageBean;
import top.huhuiyu.springboot2.entity.TbDept;
import top.huhuiyu.springboot2.service.DeptService;

import java.util.List;
import java.util.Optional;

@Service
public class DeptServiceImpl implements DeptService {
    // spring通过构造函数注入依赖对象
    private final TbDeptDAO tbDeptDAO;

    public DeptServiceImpl(TbDeptDAO tbDeptDAO) {
        this.tbDeptDAO = tbDeptDAO;
    }

    @Override
    public BaseResult<List<TbDept>> query(TbDept dept, PageBean page) throws Exception {
        // 查询条件
        dept = Optional.ofNullable(dept).orElse(new TbDept());
        if (StringUtils.hasText(dept.getDeptName())) {
            dept.setDeptName(String.format("%%%s%%", dept.getDeptName()));
        }
        // 分页处理
        page = Optional.ofNullable(page).orElse(new PageBean());
        // 启用分页查询
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        List<TbDept> list = tbDeptDAO.query(dept);

        BaseResult<List<TbDept>> result = new BaseResult<>();
        result.setCode(200);
        result.setSuccess(true);
        result.setMessage("");
        result.setData(list);
        return result;
    }

    @Override
    public BaseResult<TbDept> add(TbDept dept) throws Exception {
        BaseResult<TbDept> result = new BaseResult<>();
        int i = tbDeptDAO.add(dept);
        result.setCode(i == 1 ? 200 : 500);
        result.setSuccess(i == 1);
        result.setMessage(i == 1 ? "添加成功" : "添加失败");
        result.setData(dept);
        return result;
    }

    @Override
    public BaseResult<TbDept> update(TbDept dept) throws Exception {
        BaseResult<TbDept> result = new BaseResult<>();
        int i = tbDeptDAO.update(dept);
        result.setCode(i == 1 ? 200 : 500);
        result.setSuccess(i == 1);
        result.setMessage(i == 1 ? "修改成功" : "修改失败");
        result.setData(dept);
        return result;
    }

    @Override
    public BaseResult<TbDept> delete(TbDept dept) throws Exception {
        BaseResult<TbDept> result = new BaseResult<>();
        int i = tbDeptDAO.delete(dept);
        result.setCode(i == 1 ? 200 : 500);
        result.setSuccess(i == 1);
        result.setMessage(i == 1 ? "删除成功" : "删除失败");
        result.setData(dept);
        return result;
    }

}
