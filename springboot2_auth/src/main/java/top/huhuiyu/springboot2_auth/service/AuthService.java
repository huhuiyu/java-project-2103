package top.huhuiyu.springboot2_auth.service;

import top.huhuiyu.springboot2_auth.base.BaseResult;
import top.huhuiyu.springboot2_auth.entity.TbAuthUser;

public interface AuthService {
    BaseResult login(TbAuthUser user);
}
