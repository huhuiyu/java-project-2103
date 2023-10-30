package top.huhuiyu.springboot2.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.huhuiyu.springboot2.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppInterceptor implements HandlerInterceptor {
    private final JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 拦截器是接近servlet底层的api，所以能拿到servlet相关的api（请求，应答对象）
        // 所以拦截器的执行也在控制器切面之前完成
        String token = request.getHeader("Authorization");
        log.debug("认证token的信息：{}", token);
        // 解码token得到uid的信息
        String uid = jwtUtils.parseToken(token);
        log.debug("token中的uid：{}", uid);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("拦截器执行完毕");
    }
}
