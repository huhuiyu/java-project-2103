package top.huhuiyu.springboot2.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final String PASSWORD = "springboot2.huhuiyu.top.key";

    private static final int EXPIRATION_TIME = 45 * 60 * 1000;

    public String makeToken(String info) {
        // 过期时间设置
        Date date = new Date();
        long expire = date.getTime() + EXPIRATION_TIME;
        date.setTime(expire);
        return JWT.create().withExpiresAt(date).withClaim("info", info).sign(Algorithm.HMAC256(PASSWORD));
    }

    public String parseToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        Date expiresAt = decodedJWT.getExpiresAt();
        Date now = new Date();
        // 过期的判断
        if (now.after(expiresAt)) {
            return null;
        }
        return decodedJWT.getClaim("info").asString();
    }

}
