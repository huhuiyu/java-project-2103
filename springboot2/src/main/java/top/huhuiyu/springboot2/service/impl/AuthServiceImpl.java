package top.huhuiyu.springboot2.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.dao.TbUserDAO;
import top.huhuiyu.springboot2.dao.TbUserInfoDAO;
import top.huhuiyu.springboot2.entity.TbUser;
import top.huhuiyu.springboot2.entity.TbUserInfo;
import top.huhuiyu.springboot2.service.AuthService;
import top.huhuiyu.springboot2.utils.JwtUtils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * 用户认证相关服务实现
 *
 * @author 胡辉煜
 */
@Service
// @Transactional 注解表示开启数据库事务处理，可以作用在类或者方法上面
// rollbackFor表示发生什么异常的时候必须回滚事务
// 事务的基本概念就是所有的数据库操作同生共死
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final TbUserDAO tbUserDAO;
    private final TbUserInfoDAO tbUserInfoDAO;
    private final JwtUtils jwtUtils;

    private static final String RAND_STRINGS = "abcdefghijklmnopqrstuvwxyz0123456789";

    @Override
    public String makeRandString(int length) {
        // 字符串构造器，用于大量拼接字符串
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(RAND_STRINGS.charAt(random.nextInt(RAND_STRINGS.length())));
        }
        return sb.toString();
    }

    @Override
    public BaseResult<String> reg(TbUser tbUser) throws Exception {
        BaseResult<String> result = new BaseResult<>();
        TbUser check = tbUserDAO.queryByName(tbUser);
        if (check != null) {
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("用户名已经存在");
            return result;
        }
        // 需要生成密码盐信息对密码进行加密
        String salt = makeRandString(6);
        // 密码一次md5加密
        String pwd = tbUser.getPassword();
        pwd = DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));
        // 二次带盐加密，防止彩虹表还原信息
        pwd = pwd + salt;
        pwd = DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));
        // 添加基本信息
        tbUser.setPassword(pwd);
        tbUser.setSalt(salt);
        int i = tbUserDAO.add(tbUser);
        // 如果添加失败就中断流程，返回错误应答
        if (i != 1) {
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("用户信息添加失败，请稍后重试");
            return result;
        }
        // 添加用户附加信息，确保一对一关系的数据正常
        TbUserInfo tbUserInfo = new TbUserInfo();
        // 拿到第一步添加的主键信息写入到附件信息中
        tbUserInfo.setUserId(tbUser.getUserId());
        tbUserInfo.setSex("n");
        i = tbUserInfoDAO.add(tbUserInfo);
        result.setSuccess(i == 1);
        result.setMessage(i == 1 ? "用户添加成功" : "用户信息添加失败");
        result.setCode(i == 1 ? 200 : 500);
        return result;
    }

    @Override
    public BaseResult<TbUser> login(TbUser tbUser) {
        // 通过姓名查询用户是否存在
        TbUser check = tbUserDAO.queryByName(tbUser);
        BaseResult<TbUser> result = new BaseResult<>();
        if (check == null) {
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("用户不存在");
            return result;
        }
        // 校验密码
        String pwd = tbUser.getPassword();
        // 一次加密
        pwd = DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));
        // 二次加密
        pwd = pwd + check.getSalt();
        pwd = DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));
        if (!pwd.equalsIgnoreCase(check.getPassword())) {
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("密码不正确");
            return result;
        }
        // 用户冻结的情况
        if ("n".equalsIgnoreCase(check.getEnable())) {
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("用户已经被冻结");
            return result;
        }
        log.debug("登录的用户信息：{}", check);
        // 登录成功
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage("登录成功");
        // 传回完整用户信息（不必要的）
//         result.setData(check);
        // 发放token信息
        result.setToken(jwtUtils.makeToken(check.getUserId() + ""));
        return result;
    }


    @Override
    public BaseResult modifyPwd(String oldpwd, TbUser tbUser) {
        BaseResult result = new BaseResult();
        // 获取原生的用户信息
        TbUser check = tbUserDAO.queryByName(tbUser);
        if (check == null) {
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("用户不存在");
            return result;
        }
        // 校验原密码
        oldpwd = DigestUtils.md5DigestAsHex(oldpwd.getBytes(StandardCharsets.UTF_8));
        oldpwd = oldpwd + check.getSalt();
        oldpwd = DigestUtils.md5DigestAsHex(oldpwd.getBytes(StandardCharsets.UTF_8));
        if (!oldpwd.equalsIgnoreCase(check.getPassword())) {
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("原密码不正确");
            return result;
        }
        // 修改密码
        String newpwd = tbUser.getPassword();
        newpwd = DigestUtils.md5DigestAsHex(newpwd.getBytes(StandardCharsets.UTF_8));
        newpwd = newpwd + check.getSalt();
        newpwd = DigestUtils.md5DigestAsHex(newpwd.getBytes(StandardCharsets.UTF_8));
        check.setPassword(newpwd);
        int i = tbUserDAO.updatePwd(check);

        result.setSuccess(i == 1);
        result.setMessage(i == 1 ? "密码修改成功" : "密码修改失败");
        result.setCode(i == 1 ? 200 : 500);
        return result;

    }

}
