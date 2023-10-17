package top.huhuiyu.springboot2.ioc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IocFourFactory {
    public static IocFour make() {
        return new IocFour() {
            @Override
            public void test() {
                log.debug("ico four");
            }
        };
    }
}
