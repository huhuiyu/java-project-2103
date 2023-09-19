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

    /**
     * 添加部门信息到数据库
     *
     * @param dept 要添加的部门信息
     * @return 添加部门的结果
     * @throws Exception 处理发生异常
     */
    BaseResult<TbDept> add(TbDept dept) throws Exception;
}
