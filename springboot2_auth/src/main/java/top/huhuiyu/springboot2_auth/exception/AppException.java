package top.huhuiyu.springboot2_auth.exception;

public class AppException extends RuntimeException {
  public AppException(String message) {
    super(message);
  }

  public static AppException build(String message) {
    return new AppException(message);
  }

}
