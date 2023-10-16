package top.huhuiyu.springboot2.dao;

import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.springboot2.entity.TbUserInfo;

/**
 * tb_user_info表的数据操作类
 *
 * @author 胡辉煜
 */
@Mapper
public interface TbUserInfoDAO {
    int add(TbUserInfo tbUserInfo);
}
