package top.huhuiyu.springboot2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FormatTest {

    @Test
    public void one() {
        // log是使用{}进行占位符输出
        log.debug("log自身的模板：{}==={}", "abc", 123);
        // 字符串的格式化（占位符输出）
        // %s就是字符串占位，可以接受任意类型的变量（包括任意对象）
        // %%表示一个%号,%n表示换行
        String result = String.format("字符串的格式化：%n%s===%07d", "abc", 123);
        log.debug("{}", result);
    }
}
