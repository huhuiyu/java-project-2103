package top.huhuiyu.springboot2.ioc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class IocTwo {
    private final IocOne iocOne;
    private IocThree iocThree;
    private final IocFour iocFour;

    @Autowired
    public void setIocThree(IocThree iocThree) {
        this.iocThree = iocThree;
    }

    public void test() {
        log.debug("IocTwo.test()");
        iocOne.test();
        iocThree.test();
        iocFour.test();
        IocOne one = Container.applicationContext.getBean(IocOne.class);
        one.test();
        log.debug("容器对象获取的bean对比：{}", iocOne == one);
    }

    public boolean eq(IocOne iocOne) {
        return iocOne == this.iocOne;
    }

    public boolean eq(IocThree iocThree) {
        return iocThree == this.iocThree;
    }

}
