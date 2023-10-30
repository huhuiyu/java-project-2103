package top.huhuiyu.springboot2_auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.springboot2_auth.base.BaseResult;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/userAuth")
    public BaseResult userAuth() {
        return BaseResult.ok("权限通过");
    }

    @GetMapping("/adminAuth")
    public BaseResult adminAuth() {
        return BaseResult.ok("权限通过");
    }

    @GetMapping("/auth/{info}")
    public BaseResult auth(@PathVariable("info") String info) {
        return BaseResult.ok("权限通过" + info);
    }
}
