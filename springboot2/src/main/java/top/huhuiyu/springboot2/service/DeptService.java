package top.huhuiyu.springboot2.service;

import top.huhuiyu.springboot2.base.BasePageResult;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.entity.PageBean;
import top.huhuiyu.springboot2.entity.TbDept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门信息
     *
     * @param dept 部门查询条件
     * @param page 分页条件
     * @return 所有部门信息
     * @throws Exception 处理发生异常
     */
    BasePageResult<List<TbDept>> query(TbDept dept, PageBean page) throws Exception;

    /**
     * 添加部门信息到数据库
     *
     * @param dept 要添加的部门信息
     * @return 添加部门的结果
     * @throws Exception 处理发生异常
     */
    BaseResult<TbDept> add(TbDept dept) throws Exception;

    /**
     * 修改部门信息
     *
     * @param dept 要修改的部门信息
     * @return 修改的结果
     * @throws Exception 处理发生异常
     */
    BaseResult<TbDept> update(TbDept dept) throws Exception;

    /**
     * 删除部门
     *
     * @param dept 要删除的部门信息
     * @return 删除的结果
     * @throws Exception 处理发生异常
     */
    BaseResult<TbDept> delete(TbDept dept) throws Exception;
}
