package top.huhuiyu.springboot2_auth.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2_auth.entity.TbAuthRoleApi;

@Mapper
public interface TbAuthRoleApiDAO {
  TbAuthRoleApi queryByUser(TbAuthRoleApi tbAuthRoleApi);
}
