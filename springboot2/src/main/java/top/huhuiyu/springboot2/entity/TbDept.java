package top.huhuiyu.springboot2.entity;

import top.huhuiyu.springboot2.validate.DeptValidate;
import top.huhuiyu.springboot2.validate.RegInfo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotNull(message = "部门编号必须填写", groups = {DeptValidate.Update.class})
    @Min(message = "部门编号不能小于1", value = 1, groups = {DeptValidate.Update.class})
    private Integer deptId;

    @NotBlank(message = "部门名称必须填写", groups = {DeptValidate.Add.class, DeptValidate.Update.class})
    @Pattern(regexp = RegInfo.DEPT_NAME, message = "部门名称必须是4-16位的非空白字符", groups = {DeptValidate.Add.class, DeptValidate.Update.class})
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
