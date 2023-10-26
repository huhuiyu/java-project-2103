package top.huhuiyu.springboot2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.huhuiyu.springboot2.utils.JwtUtils;

@SpringBootTest
@Slf4j
public class TokenTest {
    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void one() throws Exception {
        String oldToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTgyODIzMTAsImluZm8iOiIxMDAifQ.eEuPzcnaATQ7u-EN5pcLz7WcxhIkxKq9AaXCZXBvJYc";
        String token = jwtUtils.makeToken("100");
        log.debug("token信息：{}", token);
        log.debug("解析结果：{},{}", jwtUtils.parseToken(token), jwtUtils.parseToken(oldToken));
    }
}
