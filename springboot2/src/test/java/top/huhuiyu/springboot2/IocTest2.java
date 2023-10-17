package top.huhuiyu.springboot2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class IocTest2 {

    private final IocJavaBean iocJavaBean;

    public IocTest2(@Autowired IocJavaBean iocJavaBean) {
        this.iocJavaBean = iocJavaBean;
    }

    @Test
    public void test() {
        iocJavaBean.test();
    }
}
