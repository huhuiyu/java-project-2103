package top.huhuiyu.springboot2.validate;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * 正则相关功能接口
 *
 * @author 胡辉煜
 */
public interface RegInfo {

    /**
     * 部门的正则校验表达式
     */
    String DEPT_NAME = "^[\\S]{4,16}$";

    /**
     * 正则校验
     *
     * @param reg  正则表达式
     * @param info 要校验的信息
     * @return 正则校验是否通过
     */
    default boolean checkReg(String reg, String info) {
        if (!StringUtils.hasText(reg)) {
            return false;
        }
        if (!StringUtils.hasText(info)) {
            return false;
        }
        return Pattern.matches(reg, info);
    }

}
