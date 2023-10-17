package top.huhuiyu.springboot2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.huhuiyu.springboot2.ioc.IocOne;
import top.huhuiyu.springboot2.ioc.IocThree;
import top.huhuiyu.springboot2.ioc.IocTwo;

@Slf4j
@SpringBootTest
public class IocDemoTest {

    @Autowired
    private IocTwo iocTwo;
    @Autowired
    private IocOne iocOne;
    @Autowired
    private IocThree iocThree;

    @Test
    public void demo() {
        iocTwo.test();
        log.debug("one相等性测试：{}", iocTwo.eq(iocOne));
        log.debug("three相等性测试：{}", iocTwo.eq(iocThree));
    }
}
