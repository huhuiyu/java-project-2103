package top.huhuiyu.springboot2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.huhuiyu.springboot2.utils.JwtUtils;

@Aspect
@Slf4j
@Component
@Order(20)
public class TokenAop implements BaseControllerAop {
    private JwtUtils jwtUtils;

    @Before("controller()")
    public void before(JoinPoint jp) {
    }
}
