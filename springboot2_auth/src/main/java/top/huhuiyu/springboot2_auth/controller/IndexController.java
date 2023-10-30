package top.huhuiyu.springboot2_auth.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.springboot2_auth.base.BaseResult;

@RestController
public class IndexController {

    @GetMapping("")
    public BaseResult index(String name) {
        return BaseResult.ok(StringUtils.hasText(name) ? name : "黑暗骑士");
    }
}
