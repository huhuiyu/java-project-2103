package top.huhuiyu.springboot2_auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.springboot2_auth.base.BaseResult;
import top.huhuiyu.springboot2_auth.entity.TbAuthUser;
import top.huhuiyu.springboot2_auth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("")
    public BaseResult login(@RequestBody TbAuthUser user) {
        return authService.login(user);
    }
}
