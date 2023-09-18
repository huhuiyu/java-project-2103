package top.huhuiyu.springboot2.service;

import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.entity.TbDept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门信息
     *
     * @return 所有部门信息
     * @throws Exception 处理发生异常
     */
    BaseResult<List<TbDept>> query() throws Exception;
}
