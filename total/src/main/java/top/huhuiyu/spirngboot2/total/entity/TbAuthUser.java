package top.huhuiyu.spirngboot2.total.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tb_auth_user")
public class TbAuthUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String username;
    private String password;
    private String salt;
    private String enable;
    private Integer roleId;
    private Date lastupdate;

    @TableField(exist = false)
    private TbAuthUserInfo tbAuthUserInfo;
}
