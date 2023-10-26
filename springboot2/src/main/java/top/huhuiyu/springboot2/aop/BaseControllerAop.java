package top.huhuiyu.springboot2.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 切面的拦截的切点定义接口
 *
 * @author 胡辉煜
 */
public interface BaseControllerAop {
    // 切点表达式的解释
    // public 表示 方法是public修饰的 * 表示任何其它修饰词汇
    // top.huhuiyu.springboot2.controller 表示要被切面拦截的包名
    // .*任意子包 *(..)表示任意方法的名和任意数量参数
    @Pointcut("execution(public * top.huhuiyu.springboot2.controller.*.*(..)) ")
    default void controller() {
    }
}
