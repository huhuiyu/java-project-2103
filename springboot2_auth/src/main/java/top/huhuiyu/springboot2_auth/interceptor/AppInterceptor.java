package top.huhuiyu.springboot2_auth.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.huhuiyu.springboot2_auth.entity.AuthInfo;
import top.huhuiyu.springboot2_auth.utils.AuthInfoUtils;
import top.huhuiyu.springboot2_auth.utils.IpUtils;
import top.huhuiyu.springboot2_auth.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppInterceptor implements HandlerInterceptor {

    public static final String TOKEN_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("拦截器前置功能");
        // 判断是否拦截的是控制器
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            // 获取控制器类的路径信息
            Class<?> clazz = hm.getBeanType();
            RequestMapping clazzMapping = clazz.getAnnotation(RequestMapping.class);
            String cpath = clazzMapping == null ? "" : clazzMapping.value()[0];
            // 获取控制器方法路径信息
            RequestMapping methodMapping = hm.getMethodAnnotation(RequestMapping.class);
            String mpath = methodMapping == null ? "" : methodMapping.value()[0];
            log.debug("拦截器拦截路径信息：{}", cpath + mpath);
            // 请求的原始路径
            String requestPath = request.getRequestURI();
            // 请求的method
            String method = request.getMethod();
            // 将相关信息放置到线程现场中(方便后续的业务逻辑中使用)
            AuthInfo authInfo = new AuthInfo();
            authInfo.setIp(IpUtils.getIpAddress());
            authInfo.setMethod(method);
            authInfo.setMappingPath(String.format("%s%s", cpath, mpath));
            authInfo.setRequestUrl(requestPath);
            // token的处理
            String token = request.getHeader(TOKEN_HEADER);
            authInfo.setToken(token);
            authInfo.setUserId(JwtUtils.parseUserIdToken(token));
            log.debug("授权信息：{}", authInfo);
            AuthInfoUtils.put(authInfo);
            log.debug("拦截器拦截请求信息：{} {}", requestPath, method);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("拦截器尾置功能");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("拦截器功能完成");
    }
}
