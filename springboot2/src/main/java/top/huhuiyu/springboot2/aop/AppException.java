package top.huhuiyu.springboot2.aop;

/**
 * 自定义异常
 *
 * @author 胡辉煜
 */
public class AppException extends Exception {
    public AppException(String message) {
        super(message);
    }

    public static AppException build(String message) {
        return new AppException(message);
    }

}
