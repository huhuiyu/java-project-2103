package top.huhuiyu.springboot2.entity;

import java.util.Date;

/**
 * tb_dept表对应的实体类
 * 表名称对应的class要满足java规范
 * 所以类名称是首字母大写，_表达式要转驼峰表达式
 * 类对应数据表，字段对应表的属性
 *
 * @author 胡辉煜
 */
public class TbDept {
    private Integer deptId;
    private String deptName;
    private String deptInfo;
    private Date lastupdate;

    public TbDept() {
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptInfo() {
        return deptInfo;
    }

    public void setDeptInfo(String deptInfo) {
        this.deptInfo = deptInfo;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }
}
