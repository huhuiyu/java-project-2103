package top.huhuiyu.springboot2.entity;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;

/**
 * 分页信息对象
 *
 * @author 胡辉煜
 */
public class PageBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int pageNumber = 1;
    private int pageSize = 5;
    private int total;
    private int pageCount = 0;

    public PageBean() {
    }

    /**
     * 通过PageInfo设置page信息
     *
     * @param pageInfo 分页信息
     */
    public void setPageInfo(PageInfo<?> pageInfo) {
        this.setTotal((int) pageInfo.getTotal());
        this.setPageNumber(pageInfo.getPageNum());
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber <= 0) {
            return;
        }
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0 || pageSize >= 1000) {
            return;
        }
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageCount() {
        if (total == 0) {
            pageCount = 0;
            return pageCount;
        }
        pageCount = total / pageSize;
        pageCount = total % pageSize == 0 ? pageCount : pageCount + 1;
        return pageCount;
    }

}
