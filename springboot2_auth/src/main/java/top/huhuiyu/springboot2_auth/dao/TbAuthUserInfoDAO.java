package top.huhuiyu.springboot2_auth.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2_auth.entity.TbAuthUserInfo;

@Mapper
public interface TbAuthUserInfoDAO {
  TbAuthUserInfo queryByKey(Integer userId);
}
