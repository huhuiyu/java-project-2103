package top.huhuiyu.springboot2.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.huhuiyu.springboot2.base.BasePageResult;
import top.huhuiyu.springboot2.base.BaseResult;
import top.huhuiyu.springboot2.dao.TbNewsDAO;
import top.huhuiyu.springboot2.entity.PageBean;
import top.huhuiyu.springboot2.entity.TbNews;
import top.huhuiyu.springboot2.service.NewsServcie;

import java.util.List;

@Service
@Transactional
public class NewsServcieImpl implements NewsServcie {
    @Autowired
    private TbNewsDAO tbNewsDAO;

    @Override
    public BaseResult delete(Integer nid) {
        int i = tbNewsDAO.delete(nid);
        return i == 1 ? BaseResult.ok("删除成功") : BaseResult.fail("删除失败");
    }

    @Override
    public BaseResult update(TbNews tbNews) {
        int i = tbNewsDAO.update(tbNews);
        return i == 1 ? BaseResult.ok("修改成功") : BaseResult.fail("修改失败");
    }

    @Override
    public BaseResult add(TbNews tbNews) {
        int i = tbNewsDAO.add(tbNews);
        return i == 1 ? BaseResult.ok("添加成功") : BaseResult.fail("添加失败");
    }

    @Override
    public BasePageResult<List<TbNews>> query(TbNews tbNews, PageBean pageBean) {
        // 处理查询的参数
        if (StringUtils.hasText(tbNews.getTitle())) {
            // like的百分号处理
            tbNews.setTitle(String.format("%%%s%%", tbNews.getTitle()));
        }
        // 开启分页
        PageHelper.startPage(pageBean.getPageNumber(), pageBean.getPageSize());
        List<TbNews> list = tbNewsDAO.query(tbNews);
        PageInfo<TbNews> page = new PageInfo<>(list);
        pageBean.setPageInfo(page);
        BasePageResult result = new BasePageResult();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage("");
        result.setPage(pageBean);
        result.setData(list);
        return result;
    }

    @Override
    public BaseResult<TbNews> queryByKey(Integer nid) {
        return BaseResult.ok("", tbNewsDAO.queryByKey(nid));
    }

}
