package top.huhuiyu.springboot2.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_news表的实体类
 *
 * @author 胡辉煜
 */
@Data
public class TbNews implements Serializable {
    private final static long serialVersionUID = 1L;
    private Integer nid;
    private String title;
    private String info;
    private String source;
    private Date lastupdate;
}
