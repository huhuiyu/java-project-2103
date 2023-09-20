package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2.entity.TbDept;

import java.util.List;

@Mapper
public interface TbDeptDAO {
    List<TbDept> query() throws Exception;

    int add(TbDept dept) throws Exception;

    int update(TbDept dept) throws Exception;

    int delete(TbDept dept) throws Exception;
}
