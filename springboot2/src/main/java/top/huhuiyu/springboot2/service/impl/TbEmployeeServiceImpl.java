package top.huhuiyu.springboot2.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.huhuiyu.springboot2.base.BasePageResult;
import top.huhuiyu.springboot2.dao.TbEmployeeDAO;
import top.huhuiyu.springboot2.entity.PageBean;
import top.huhuiyu.springboot2.entity.TbEmployee;
import top.huhuiyu.springboot2.service.TbEmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class TbEmployeeServiceImpl implements TbEmployeeService {

    @Autowired
    private TbEmployeeDAO tbEmployeeDAO;

    @Override
    public BasePageResult<List<TbEmployee>> query(TbEmployee employee, PageBean page) throws Exception {
        page = Optional.ofNullable(page).orElse(new PageBean());
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        List<TbEmployee> list = tbEmployeeDAO.query(employee);
        page.setPageInfo(new PageInfo<>(list));

        BasePageResult<List<TbEmployee>> result = new BasePageResult<>();
        result.setCode(200);
        result.setSuccess(true);
        result.setMessage("");
        result.setData(list);
        result.setPage(page);
        return result;
    }
}
