package top.huhuiyu.springboot2.ioc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IocOne {

    public void test() {
        log.debug("ioc one");
    }
}
