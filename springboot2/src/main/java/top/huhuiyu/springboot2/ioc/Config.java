package top.huhuiyu.springboot2.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public IocFour iocFour() {
        return IocFourFactory.make();
    }
}
