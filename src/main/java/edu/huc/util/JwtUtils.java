package edu.huc.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@ConfigurationProperties(prefix = "config.jwt")
public class JwtUtils {
    private String secret;
    private long expire;
    private String Header;

    /**
     * 生成token
     */
    public String generateToken(int userId){
        Date nowDate = new Date();
        //token过期时间设置
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ","jwt")
                .setSubject(userId+"")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public Claims getClaimByToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * token是否过期
     */
    public boolean isTokenExpired(Date expiration){
        return expiration.before(new Date());
    }
}
