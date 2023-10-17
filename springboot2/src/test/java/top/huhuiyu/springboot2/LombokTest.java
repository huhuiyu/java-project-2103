package top.huhuiyu.springboot2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class LombokTest {

    @Test
    public void one() {
        LombokEntity entity = new LombokEntity();
        entity.setName("huhuiyu");
        entity.setId(100);
        log.debug("{}", entity);
    }

    @Test
    public void two() {
        IocJavaBean bean = new IocJavaBean();
        bean.test();

        IocJavaBean bean2 = new IocJavaBean();
        bean2.test();

        log.debug("测试相等性{}", bean2 == bean);
    }
    // @Autowired表示注入一个spring容器管理的对象
    // 默认装载的策略是找到一个spring容器管理的被注解对象同类型的注入
    @Autowired
    private IocJavaBean iocJavaBean;

    @Test
    public void three() {
        iocJavaBean.test();
    }
}
