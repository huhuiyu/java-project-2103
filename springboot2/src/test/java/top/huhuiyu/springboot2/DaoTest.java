package top.huhuiyu.springboot2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.huhuiyu.springboot2.dao.TbDeptDAO;
import top.huhuiyu.springboot2.entity.TbDept;
import top.huhuiyu.springboot2.utils.JsonUtils;

import java.util.List;

@SpringBootTest
public class DaoTest {
    private static final Logger logger = LoggerFactory.getLogger(Springboot2Application.class);

    // @Autowired 表示该注解下面的类是由spring容器管理和注入
    // 下面是几个常见的声明spring管理的对象注解
    //@Component 组件
    //@Service 服务层
    //@Configuration 配置
    //@Bean java对象
    @Autowired
    private TbDeptDAO tbDeptDAO;

    @Test
    public void one() throws Exception {
        List<TbDept> list = tbDeptDAO.query();
        for (TbDept dept : list) {
            logger.debug("{}", JsonUtils.stringify(dept));
        }
    }

}
