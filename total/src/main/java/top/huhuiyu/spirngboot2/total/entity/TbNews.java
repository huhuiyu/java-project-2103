package top.huhuiyu.spirngboot2.total.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("tb_news")
@Data
public class TbNews implements Serializable {
    private final static long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer nid;
    private String title;
    private String info;
    private String source;
    @TableField(updateStrategy = FieldStrategy.NEVER, insertStrategy = FieldStrategy.NEVER)
    private Date lastupdate;
}
