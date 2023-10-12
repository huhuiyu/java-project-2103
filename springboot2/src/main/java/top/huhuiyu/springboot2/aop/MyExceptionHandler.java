package top.huhuiyu.springboot2.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.huhuiyu.springboot2.base.BaseResult;

import java.sql.SQLException;
import java.util.List;

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

    /**
     * json提交数据校验错误处理
     *
     * @param ex 异常信息
     * @return 标准应答结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResult<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // 获取JSON（@RequestBody）校验错误信息
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        // 拼接错误信息
        StringBuilder sb = new StringBuilder();
        for (ObjectError oe : errors) {
            sb.append(oe.getDefaultMessage()).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        BaseResult<String> result = new BaseResult<>();
        result.setSuccess(false);
        result.setCode(501);
        result.setMessage(sb.toString());
        logger.debug("校验异常：{}", ex.getMessage());
        return result;
    }

    /**
     * form提交数据校验错误处理
     *
     * @param ex 异常信息
     * @return 标准应答结果
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public BaseResult<String> handleBindException(BindException ex) {
        // 获取表单校验错误信息
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        // 拼接错误信息
        StringBuilder sb = new StringBuilder();
        for (ObjectError oe : errors) {
            sb.append(oe.getDefaultMessage()).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        BaseResult<String> result = new BaseResult<>();
        result.setSuccess(false);
        result.setCode(502);
        result.setMessage(sb.toString());
        logger.debug("校验异常：{}", ex.getMessage());
        return result;
    }

    /**
     * 数据库操作异常处理
     *
     * @param ex 异常信息
     * @return 标准应答结果
     */
    @ExceptionHandler({DataAccessException.class})
    @ResponseBody
    public BaseResult<String> handler(DataAccessException ex) {
        logger.error("数据库操作异常", ex);
        BaseResult<String> result = new BaseResult<>();
        result.setSuccess(false);
        result.setCode(500);
        result.setMessage("数据信息异常");
        return result;
    }

    /**
     * 数据库异常处理
     *
     * @param ex 异常信息
     * @return 标准应答结果
     */
    @ExceptionHandler({SQLException.class})
    @ResponseBody
    public BaseResult<String> handler(SQLException ex) {
        logger.error("sql异常", ex);
        BaseResult<String> result = new BaseResult<>();
        result.setSuccess(false);
        result.setCode(500);
        result.setMessage("数据处理发生异常");
        return result;
    }

    /**
     * 标准异常处理
     *
     * @param ex 异常信息
     * @return 标准应答结果
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public BaseResult<String> handler(Exception ex) {
        logger.error("系统异常", ex);
        BaseResult<String> result = new BaseResult<>();
        result.setCode(500);
        result.setSuccess(false);
        result.setMessage(ex.getMessage());
        return result;
    }

}
