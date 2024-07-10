package com.gpqh.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your_secret_key";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public String generateToken(String username) {
        String token = JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .sign(algorithm);
        redisTemplate.opsForValue().set("jwt:",token, 10, TimeUnit.HOURS);
        return token;
    }

    public DecodedJWT verifyToken(String token) {
        if (isTokenBlacklisted(token)) {
            throw new RuntimeException("Token is blacklisted");
        }
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getSubject();
    }

    public void invalidateToken(String token) {
        redisTemplate.delete(token);
    }

    public void blacklistToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        long expirationTime = decodedJWT.getExpiresAt().getTime() - System.currentTimeMillis();
        redisTemplate.opsForValue().set("blacklist_" + token, token, expirationTime, TimeUnit.MILLISECONDS);
    }

    public boolean isTokenBlacklisted(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey("blacklist_" + token));
    }
}