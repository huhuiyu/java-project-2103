package top.huhuiyu.springboot2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTest {
    private static final Logger logger = LoggerFactory.getLogger(Springboot2Application.class);

    @Test
    public void one() throws Exception {

        System.out.println(String.format("欢迎:%s(%s)", "黑暗骑士", "男"));
        logger.error("日志输出", new Exception("一条错误信息"));
        logger.info("日志输出：{}", "一条日志信息");
        logger.debug("日志输出：{}", "一条测试信息");

//        ctrl + alt + l 格式化
//        ctrl + 空格 代码提示
    }


}
