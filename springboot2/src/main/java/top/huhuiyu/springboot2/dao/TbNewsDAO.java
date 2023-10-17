package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2.entity.TbNews;

@Mapper
public interface TbNewsDAO {

    int add(TbNews tbNews);
}
