package top.huhuiyu.springboot2.ioc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Scope("prototype")
public class IocThree {
    public void test() {
        log.debug("ioc three");
    }
}
