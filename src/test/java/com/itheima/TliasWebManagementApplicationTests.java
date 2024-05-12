package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testUUID() {
        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    /**
     * 测试生成JWT令牌
     */
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "zzh-loren") // 签名算法 密钥不能太短，最短4个字符
                .setClaims(claims) // 自定义内容 payload部分
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))// 设置令牌过期时间为3600 * 1000毫秒后(1小时后)
                .compact(); // 生成令牌
        System.out.println(jwt);
    }

    /**
     * 解析JWT令牌
     */
    @Test
    public void testParseJwt() {
        Claims body = Jwts.parser()
                .setSigningKey("zzh-loren") //指定密钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcxNTUwMDQ1OH0.HcpzllHBF4ga8fsO1sl_hc7lP67VYJjM4ENyxY38Kbo")
                .getBody();
        System.out.println(body);
    }


}
