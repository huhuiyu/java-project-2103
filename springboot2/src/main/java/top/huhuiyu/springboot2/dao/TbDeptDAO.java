package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.huhuiyu.springboot2.entity.TbDept;

import java.util.List;

@Mapper
public interface TbDeptDAO {
    List<TbDept> query() throws Exception;

}
