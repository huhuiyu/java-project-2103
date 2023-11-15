package top.huhuiyu.spirngboot2.total.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tb_info")
public class TbInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String info;
    @TableField(insertStrategy = FieldStrategy.NEVER,updateStrategy = FieldStrategy.NEVER)
    private Integer hits;
    @TableLogic
    private String deleted;
    @TableField(insertStrategy = FieldStrategy.NEVER,updateStrategy = FieldStrategy.NEVER)
    private Date lastupdate;
}
