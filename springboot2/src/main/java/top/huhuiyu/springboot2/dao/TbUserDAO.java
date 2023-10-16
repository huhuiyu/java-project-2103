package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2.entity.TbUser;

@Mapper
public interface TbUserDAO {
    int add(TbUser tbUser);
}
