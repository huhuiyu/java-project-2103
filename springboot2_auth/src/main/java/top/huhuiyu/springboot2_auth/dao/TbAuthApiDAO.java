package top.huhuiyu.springboot2_auth.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2_auth.entity.TbAuthApi;

@Mapper
public interface TbAuthApiDAO {
  TbAuthApi queryByUrl(TbAuthApi tbAuthApi);
}
