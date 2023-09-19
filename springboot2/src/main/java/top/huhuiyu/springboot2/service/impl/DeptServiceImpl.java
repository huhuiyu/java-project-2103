package top.huhuiyu.springboot2.service.impl;

import org.springframework.stereotype.Service;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.dao.TbDeptDAO;
import top.huhuiyu.springboot2.entity.TbDept;
import top.huhuiyu.springboot2.service.DeptService;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    // spring通过构造函数注入依赖对象
    private final TbDeptDAO tbDeptDAO;

    public DeptServiceImpl(TbDeptDAO tbDeptDAO) {
        this.tbDeptDAO = tbDeptDAO;
    }

    @Override
    public BaseResult<List<TbDept>> query() throws Exception {
        BaseResult<List<TbDept>> result = new BaseResult<>();
        result.setCode(200);
        result.setSuccess(true);
        result.setMessage("");
        result.setData(tbDeptDAO.query());
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

}
