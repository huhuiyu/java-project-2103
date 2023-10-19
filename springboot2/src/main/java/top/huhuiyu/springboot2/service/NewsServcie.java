package top.huhuiyu.springboot2.service;

import top.huhuiyu.springboot2.base.BasePageResult;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.entity.PageBean;
import top.huhuiyu.springboot2.entity.TbNews;

import java.util.List;

public interface NewsServcie {
    BaseResult delete(Integer nid);

    BaseResult update(TbNews tbNews);

    BaseResult add(TbNews tbNews);

    BasePageResult<List<TbNews>> query(TbNews tbNews, PageBean pageBean);

    BaseResult<TbNews> queryByKey(Integer nid);
}
