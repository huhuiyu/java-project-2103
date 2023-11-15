package top.huhuiyu.spirngboot2.total.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.spirngboot2.total.entity.TbAuthUser;

@Mapper
public interface TbAuthUserDAO extends BaseMapper<TbAuthUser> {
}
