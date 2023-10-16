package top.huhuiyu.springboot2.base;

//基础应答对象 ctrl + alt + l
public class BaseResult<T> {
    private int code = 500;
    private boolean success = false;
    private String message;
    private T data;

    public BaseResult() {
    }

//      result.setSuccess(false);
//            result.setCode(500);
//            result.setMessage("用户不存在");
//            return result;

//    result.setSuccess(true);
//        result.setCode(200);
//        result.setMessage("登录成功");

    public static BaseResult ok(String message) {
        BaseResult result = new BaseResult();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage(message);
        return result;
    }

    public static BaseResult fail(String message) {
        BaseResult result = new BaseResult();
        result.setSuccess(false);
        result.setCode(500);
        result.setMessage(message);
        return result;
    }

    public static <T> BaseResult<T> ok(String message, T data) {
        BaseResult result = new BaseResult();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
