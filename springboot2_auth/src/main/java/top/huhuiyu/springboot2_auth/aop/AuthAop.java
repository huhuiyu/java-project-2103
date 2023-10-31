package top.huhuiyu.springboot2_auth.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.huhuiyu.springboot2_auth.exception.AppException;
import top.huhuiyu.springboot2_auth.service.AuthService;

@Aspect
@Component
@Slf4j
@Order(20)
@RequiredArgsConstructor
public class AuthAop implements BaseControllerAop {

    private final AuthService authService;

    @Before("controller()")
    public void before(JoinPoint jp) {
        if (!authService.checkAuth()) {
            throw AppException.build("请使用对应角色的用户登录进行访问");
        }
    }

}
