package top.huhuiyu.springboot2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.springboot2.base.BaseResult;

import java.util.Date;

// class都是首字母大写
// 字段，变量，方法名都是首字母小写
// 常量是全字母大写
@RestController
public class Index {

    @GetMapping("")
    public BaseResult<String> echo(String name) throws Exception {
        BaseResult<String> result = new BaseResult<>();
        result.setCode(200);
        result.setSuccess(true);
        result.setMessage("欢迎使用黑暗骑士后端接口");
        result.setData(String.format("欢迎：'%s'", name));
        return result;
    }

    @GetMapping("/test/date")
    public BaseResult<Date> testDate() throws Exception {
        BaseResult<Date> result = new BaseResult<>();
        result.setCode(200);
        result.setSuccess(true);
        result.setMessage("");
        result.setData(new Date());
        return result;
    }
}
