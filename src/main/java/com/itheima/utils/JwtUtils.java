package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey = "zzh-loren"; //签名算法密钥
    private static Long expire = 43200000L; // 过期时间-毫秒  12h

    /**
     * 生成jwt令牌
     * @param claims
     * @return
     */
    public static String generateJwt(Map<String, Object> claims) {
        String jwt_token = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt_token;
    }

    /**
     * 解析jwt令牌
     * @param jwt
     * @return
     */
    public static Claims parseJwt(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey) // 签名算法密钥
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
