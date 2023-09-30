package top.huhuiyu.springboot2.base;

import top.huhuiyu.springboot2.entity.PageBean;

public class BasePageResult<T> extends BaseResult<T> {
    private PageBean page;

    public BasePageResult() {
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }
}
