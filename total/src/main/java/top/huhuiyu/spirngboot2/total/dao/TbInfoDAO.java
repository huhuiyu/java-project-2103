package top.huhuiyu.spirngboot2.total.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.huhuiyu.spirngboot2.total.entity.TbInfo;

@Mapper
public interface TbInfoDAO extends BaseMapper<TbInfo> {
    @Update("update tb_info set hits=hits+1 where id=#{id}")
    int updateHits(Integer id);

    @Select("select * from tb_info where id=#{id}")
    TbInfo queryById(Integer id);
}
