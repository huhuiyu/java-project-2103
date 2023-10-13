package top.huhuiyu.springboot2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.huhuiyu.springboot2.validate.RegInfo;

import java.util.regex.Pattern;

public class TestReg {
    private final Logger logger = LoggerFactory.getLogger(TestReg.class);

    @Test
    public void reg() {
        String zz = "^[0-9a-zA-Z_-]{4,16}$";
        String info = "黑暗骑士";
        logger.debug("校验结果；{}", Pattern.matches(zz, info));
        info = "a9";
        logger.debug("校验结果；{}", Pattern.matches(zz, info));
        info = "a_910-bZ";
        logger.debug("校验结果；{}", Pattern.matches(zz, info));
        info = "a910bZaaaaasadfasdfsdafasdfdsafsdafsdafsdafasfasdfsdafsadf";
        logger.debug("校验结果；{}", Pattern.matches(zz, info));
        zz = "^[\\S]{4,}$";
        logger.debug("校验结果：{}", Pattern.matches(zz, "黑暗骑士"));
        logger.debug("校验结果：{}", Pattern.matches(zz, "abcd加999"));
        logger.debug("校验结果：{}", Pattern.matches(zz, "abcd 999"));
    }
}
