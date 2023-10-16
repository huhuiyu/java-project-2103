package top.huhuiyu.springboot2.service;

import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.entity.TbUser;

/**
 * 用户认证相关服务
 *
 * @author 胡辉煜
 */
public interface AuthService {
    String makeRandString(int length);

    BaseResult<String> reg(TbUser tbUser) throws Exception;

    BaseResult<TbUser> login(TbUser tbUser);

    BaseResult modifyPwd(String oldpwd, TbUser tbUser);
}
