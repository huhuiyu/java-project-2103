package top.huhuiyu.springboot2.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.huhuiyu.springboot2.base.BaseResult;

/**
 * 拦截控制器异常的切面处理
 *
 * @author 胡辉煜
 */
@ControllerAdvice
public class MyExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler({AppException.class})
    @ResponseBody
    public BaseResult<String> handler(AppException ex) {
        logger.error("控制器处理发生异常", ex);
        BaseResult<String> result = new BaseResult<>();
        result.setCode(500);
        result.setSuccess(false);
        result.setMessage("网站忙，请稍后重试");
        result.setData(ex.getMessage());
        return result;
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public BaseResult<String> handler(Exception ex) {
        logger.error("控制器处理发生异常", ex);
        BaseResult<String> result = new BaseResult<>();
        result.setCode(500);
        result.setSuccess(false);
        result.setMessage("网站忙，请稍后重试");
        return result;
    }

}
