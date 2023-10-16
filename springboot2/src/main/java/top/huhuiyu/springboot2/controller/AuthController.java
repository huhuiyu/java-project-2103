package top.huhuiyu.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.entity.TbUser;
import top.huhuiyu.springboot2.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/reg")
    public BaseResult<String> reg(TbUser tbUser) throws Exception {
        return authService.reg(tbUser);
    }

    @PostMapping("/login")
    public BaseResult<TbUser> login(TbUser tbUser) {
        return authService.login(tbUser);
    }

    @PostMapping("/modifyPwd")
    public BaseResult modifyPwd(String oldpwd, TbUser tbUser) throws Exception {
        return authService.modifyPwd(oldpwd, tbUser);
    }

}
