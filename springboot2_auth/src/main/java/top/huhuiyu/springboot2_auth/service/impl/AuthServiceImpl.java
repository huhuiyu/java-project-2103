package top.huhuiyu.springboot2_auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.huhuiyu.springboot2_auth.base.BaseResult;
import top.huhuiyu.springboot2_auth.dao.TbAuthUserDAO;
import top.huhuiyu.springboot2_auth.entity.TbAuthUser;
import top.huhuiyu.springboot2_auth.service.AuthService;
import top.huhuiyu.springboot2_auth.utils.JwtUtils;
import top.huhuiyu.springboot2_auth.utils.Utils;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final TbAuthUserDAO tbAuthUserDAO;

    @Override
    public BaseResult login(TbAuthUser user) {
        // 判断用户是否存在
        TbAuthUser check = tbAuthUserDAO.queryByName(user);
        if (check == null) {
            return BaseResult.fail("用户不存在");
        }
        // 判断用户是否禁用
        if ("n".equalsIgnoreCase(check.getEnable())) {
            return BaseResult.fail("用户已禁用");
        }
        // 校验密码
        String pwd = Utils.saltMd5(user.getPassword(), check.getSalt());
        if (!check.getPassword().equalsIgnoreCase(pwd)) {
            return BaseResult.fail("密码错误");
        }
        // 用登录用户id生成jwt的token
        String token = JwtUtils.makeUserIdToken(check.getUserId());
        BaseResult result = BaseResult.ok("登录成功");
        // 递交token给客户端
        result.setToken(token);
        return result;
    }
}
