package top.huhuiyu.spirngboot2.total.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tb_auth_user_info")
public class TbAuthUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.NONE)
    private Integer userId;
    private String nickname;
    private String sex;
    private String email;
    private String phone;
    private String imgurl;
    private Date lastupdate;
}
