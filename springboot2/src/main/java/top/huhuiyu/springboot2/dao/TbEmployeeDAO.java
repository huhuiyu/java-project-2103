package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2.entity.TbEmployee;

import java.util.List;

@Mapper
public interface TbEmployeeDAO {
    public List<TbEmployee> query(TbEmployee tbEmployee) throws Exception;
}
