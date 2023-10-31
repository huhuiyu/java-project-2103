package top.huhuiyu.springboot2_auth.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import top.huhuiyu.springboot2_auth.utils.IpUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppListener implements ApplicationListener<ApplicationReadyEvent> {
    // springboot的应用启动完毕的监听器
    public static final String LOCAL_IP = "127.0.0.1";
    // springboot的环境配置信息
    private final Environment environment;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 获取应用的端口号
        Integer port = environment.getProperty("server.port", Integer.class);
        // 获取本地ip地址
        String ip = IpUtils.getLocalIp();
        // 显示应用的完整链接
        log.info("===============================================================");
        log.info("应用接口的路径：http://{}:{}", ip, port);
        log.info("应用接口的路径：http://{}:{}", LOCAL_IP, port);
        log.info("===============================================================");
    }

}
