package top.huhuiyu.springboot2.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_user表的实体类
 *
 * @author 胡辉煜
 */
public class TbUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String username;
    private String password;
    private String salt;
    private String enable;
    private Date lastupdate;

    public TbUser() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }
}
