package top.huhuiyu.springboot2_auth.utils;

import top.huhuiyu.springboot2_auth.entity.AuthInfo;

public class AuthInfoUtils {
    private static ThreadLocal<AuthInfo> threadLocal = new ThreadLocal<>();

    public static AuthInfo authInfo() {
        return threadLocal.get();
    }

    public static void put(AuthInfo authInfo) {
        threadLocal.set(authInfo);
    }

}
