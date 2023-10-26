package top.huhuiyu.springboot2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// 启用切面
@Aspect
@Slf4j
@Component
public class LoggerAop implements BaseControllerAop {

    // @Before表示进入被切面的对象之前执行
    // 参数是切点表达式注解的方法，也就是要切面的对象
    @Before("controller()")
    public void before(JoinPoint joinPoint) {
        log.debug("进入=======>{}", joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            log.debug("没有参数");
        } else {
            for (Object obj : args) {
                log.debug("参数：{},{}", obj.getClass().getName(), obj);
            }
        }
    }

}
