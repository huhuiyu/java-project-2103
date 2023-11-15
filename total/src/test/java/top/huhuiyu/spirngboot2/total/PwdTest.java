package top.huhuiyu.spirngboot2.total;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class PwdTest {
    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void database() {
        log.debug("数据库用户名：{}",stringEncryptor.encrypt("student"));
        log.debug("数据库密码：{}",stringEncryptor.encrypt("pwd4_student"));
    }
}
