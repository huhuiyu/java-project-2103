package top.huhuiyu.spirngboot2.total;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;
import top.huhuiyu.spirngboot2.total.dao.TbInfoDAO;
import top.huhuiyu.spirngboot2.total.dao.TbNewsDAO;
import top.huhuiyu.spirngboot2.total.entity.TbInfo;
import top.huhuiyu.spirngboot2.total.entity.TbNews;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootTest
public class DaoTest {
    @Autowired
    private TbNewsDAO tbNewsDAO;
    @Autowired
    private TbInfoDAO tbInfoDAO;
    private Random random = new Random();

    @Test
    public void insert() {
        TbNews news = new TbNews();
        news.setLastupdate(new Date());

        news.setSource("https://huhuiyu.top");
        news.setInfo("新闻内容" + random.nextInt());
        news.setTitle("新闻标题" + random.nextInt());
        int i = tbNewsDAO.insert(news);
        log.debug("添加的结果：{},{}", i, news);
    }

    @Test
    public void query() {
        // 分页
        IPage<TbNews> page = new Page<>();
        page.setCurrent(1);
        page.setSize(7);
        // 查询条件
        TbNews news = new TbNews();
        news.setTitle("科技");
        QueryWrapper<TbNews> wrapper = new QueryWrapper<>();
        wrapper.select("nid", "title", "source", "lastupdate");
        // 第一个参数是逻辑表达式，为真才会启用条件查询,2,3字段分别表示要操作的字段和值
        wrapper.like(StringUtils.hasText(news.getTitle()), "title", news.getTitle());

        page = tbNewsDAO.selectPage(page, wrapper);
        // 分页的结果
        log.debug("分页信息：{},{},{}", page.getTotal(), page.getPages(), page.getSize());
        // 查询结果
        List<TbNews> list = page.getRecords();
        list.forEach(info -> {
            log.debug("查询结果：{}", info);
        });
    }

    @Test
    public void insertInfo() {
        TbInfo info = new TbInfo();
        info.setTitle("标题" + random.nextInt());
        info.setInfo("内容====>" + random.nextInt());
        info.setHits(1000);
        tbInfoDAO.insert(info);
        log.debug("添加的结果：{}", info);
    }

    @Test
    public void updateInfo() {
        TbInfo info = new TbInfo();
        info.setId(1);
        info.setTitle("标题修改" + random.nextInt());
        info.setInfo("内容修改====>" + random.nextInt());
        info.setHits(1000);
        tbInfoDAO.updateById(info);
        log.debug("修改的结果：{}", info);
    }

    @Test
    public void updateHits() {
        int i = tbInfoDAO.updateHits(2);
        log.debug("修改的结果：{}", i);
    }

    @Test
    public void deleteInfo() {
        int id = 3;
        TbInfo info = new TbInfo();
        info.setId(id);
        info = tbInfoDAO.queryById(id);
        log.debug("自建主键查询的结果：{}", info);
        info = tbInfoDAO.selectById(info);
        log.debug("主键查询的结果：{}", info);
        int i = tbInfoDAO.deleteById(id);
        log.debug("删除的结果：{}", i);
    }

}
