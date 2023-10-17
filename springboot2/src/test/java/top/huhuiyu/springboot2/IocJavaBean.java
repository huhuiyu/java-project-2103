package top.huhuiyu.springboot2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
// @Component表示将当前类作为一个组件注入到spring容器进行管理
// 常用的注入注解有：@Service,@Repository, @Controller
// 效果其实是一样的，只是为了区分被注入的
@Component
public class IocJavaBean {
    public void test() {
        log.debug("我是一个spring管理的对象");
    }
}
