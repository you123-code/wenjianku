package com.demo.gateway.server.service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author youwei
 * @version 1.0
 * @date 2022/3/31 17:44
 */
public class JwtTokenUtil implements Serializable {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims getClaimsFromToken(String token, String secret) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


    /**
     * 生成签名
     */
    public static String generateSign(Map<String, String> sub, String secret) {
        try {
            Map<String, Object> claims = new HashMap<>(2);
            Map<String, String> userMap = new HashMap<>(2);
            claims.put("sub", objectMapper.writeValueAsString(sub));
            claims.put("created", LocalDateTime.now().toString());

            return generateToken(claims, secret);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims, String secret) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
