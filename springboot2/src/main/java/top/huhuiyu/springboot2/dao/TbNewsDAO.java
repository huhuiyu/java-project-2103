package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2.entity.TbNews;

import java.util.List;

@Mapper
public interface TbNewsDAO {
    int delete(Integer nid);

    int update(TbNews tbNews);

    int add(TbNews tbNews);

    List<TbNews> query(TbNews tbNews);

    TbNews queryByKey(Integer nid);
}
