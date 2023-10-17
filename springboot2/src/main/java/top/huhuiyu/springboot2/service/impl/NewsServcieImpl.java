package top.huhuiyu.springboot2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.dao.TbNewsDAO;
import top.huhuiyu.springboot2.entity.TbNews;
import top.huhuiyu.springboot2.service.NewsServcie;

@Service
@Transactional
public class NewsServcieImpl implements NewsServcie {
    @Autowired
    private TbNewsDAO tbNewsDAO;

    public BaseResult add(TbNews tbNews) {
        int i = tbNewsDAO.add(tbNews);
        return i == 1 ? BaseResult.ok("添加成功") : BaseResult.fail("添加失败");
    }

}
