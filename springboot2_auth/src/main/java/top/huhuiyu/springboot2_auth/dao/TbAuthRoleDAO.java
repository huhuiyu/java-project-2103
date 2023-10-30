package top.huhuiyu.springboot2_auth.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2_auth.entity.TbAuthRole;

@Mapper
public interface TbAuthRoleDAO {

  TbAuthRole queryByKey(Integer roleId);
}
