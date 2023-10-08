package top.huhuiyu.springboot2.service;

import top.huhuiyu.springboot2.base.BasePageResult;
import top.huhuiyu.springboot2.entity.PageBean;
import top.huhuiyu.springboot2.entity.TbEmployee;

import java.util.List;

public interface TbEmployeeService {
    BasePageResult<List<TbEmployee>> query(TbEmployee employee, PageBean page) throws Exception;

    BasePageResult<List<TbEmployee>> queryByAssociation(TbEmployee employee, PageBean page) throws Exception;
}
