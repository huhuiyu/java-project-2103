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
}
