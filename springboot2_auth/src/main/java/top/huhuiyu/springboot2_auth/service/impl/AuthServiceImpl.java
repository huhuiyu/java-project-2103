package top.huhuiyu.springboot2_auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.huhuiyu.springboot2_auth.base.BaseResult;
import top.huhuiyu.springboot2_auth.dao.TbAuthApiDAO;
import top.huhuiyu.springboot2_auth.dao.TbAuthRoleApiDAO;
import top.huhuiyu.springboot2_auth.dao.TbAuthUserDAO;
import top.huhuiyu.springboot2_auth.entity.AuthInfo;
import top.huhuiyu.springboot2_auth.entity.TbAuthApi;
import top.huhuiyu.springboot2_auth.entity.TbAuthRoleApi;
import top.huhuiyu.springboot2_auth.entity.TbAuthUser;
import top.huhuiyu.springboot2_auth.service.AuthService;
import top.huhuiyu.springboot2_auth.utils.AuthInfoUtils;
import top.huhuiyu.springboot2_auth.utils.JwtUtils;
import top.huhuiyu.springboot2_auth.utils.Utils;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final TbAuthUserDAO tbAuthUserDAO;
    private final TbAuthApiDAO tbAuthApiDAO;
    private final TbAuthRoleApiDAO tbAuthRoleApiDAO;

    @Override
    public boolean checkAuth() {
        // 检查当前用户是否拥有路径访问权限
        AuthInfo authInfo = AuthInfoUtils.authInfo();
        // 查询当前访问路径是否被数据库管理
        TbAuthApi tbAuthApi = new TbAuthApi();
        tbAuthApi.setUrl(authInfo.getMappingPath());
        tbAuthApi.setMethod(authInfo.getMethod());
        tbAuthApi = tbAuthApiDAO.queryByUrl(tbAuthApi);
        if (tbAuthApi == null) {
            // 不被管理的路径直接通过
            return true;
        }
        log.debug("被管理的api：{}", tbAuthApi);
        if (authInfo.getUserId() == null) {
            // 请求中没有登录用户的信息
            return false;
        }
        // 校验当前用户是否用户菜单的访问权限
        TbAuthRoleApi tbAuthRoleApi = new TbAuthRoleApi();
        tbAuthRoleApi.setApiId(tbAuthApi.getApiId());
        // 角色传入的是用户id，查询会将用户id转换成对应的角色id
        tbAuthRoleApi.setRoleId(authInfo.getUserId());
        tbAuthRoleApi = tbAuthRoleApiDAO.queryByUser(tbAuthRoleApi);
        if (tbAuthRoleApi == null) {
            // 没有记录就没有通过授权
            return false;
        }
        return true;
    }


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
