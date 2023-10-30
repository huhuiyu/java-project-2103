package top.huhuiyu.springboot2_auth.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2_auth.entity.TbAuthUser;

@Mapper
public interface TbAuthUserDAO {
  TbAuthUser queryByName(TbAuthUser user);

  TbAuthUser queryByKey(Integer userId);

}
