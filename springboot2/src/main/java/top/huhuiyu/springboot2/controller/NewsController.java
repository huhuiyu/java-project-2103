package top.huhuiyu.springboot2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.huhuiyu.springboot2.base.BasePageResult;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.entity.PageBean;
import top.huhuiyu.springboot2.entity.TbNews;
import top.huhuiyu.springboot2.service.NewsServcie;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    // 构造函数注入依赖
    private final NewsServcie newsService;

    // restful规范，添加数据一定是post请求
    // 请求中的数据要求是json格式
    // 后端获取的时候需要使用@RequestBody标注请求中的数据是json格式
    // @RequestBody在请求方法中只能出现一次
    @PostMapping("")
    public BaseResult add(@RequestBody TbNews tbNews) {
        return newsService.add(tbNews);
    }

    @GetMapping("")
    public BasePageResult<List<TbNews>> query(TbNews news, PageBean pageBean) {
        return newsService.query(news, pageBean);
    }

    @GetMapping("/{nid}")
    public BaseResult<TbNews> queryByKey(@PathVariable("nid") Integer nid) {
        return newsService.queryByKey(nid);
    }

    @PutMapping("/{nid}")
    public BaseResult update(@RequestBody TbNews tbNews, @PathVariable("nid") Integer nid) {
        tbNews.setNid(nid);
        return newsService.update(tbNews);
    }

    @DeleteMapping("/{nid}")
    public BaseResult delete(@PathVariable("nid") Integer nid) {
        return newsService.delete(nid);
    }

}
