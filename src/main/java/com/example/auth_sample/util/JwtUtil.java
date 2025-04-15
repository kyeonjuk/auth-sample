package com.example.auth_sample.util;

import com.example.auth_sample.model.Employee;
import com.example.auth_sample.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private static final long expirationTimeInMills = 1000 * 60 * 60;   // 1hour

    // JWT 생성자 - 비밀키 가져오기
    public JwtUtil(@Value("${secret-key}") String secretKeyValue) {
        // 가져온 비밀키로 SHA-256 알고리즘에 적합한 비밀키 생성
        secretKey = Keys.hmacShaKeyFor(secretKeyValue.getBytes());
    }

    public String createToken(Employee employee) {

        Date now = new Date();
        Date expireAt = new Date(now.getTime() + expirationTimeInMills);

        // Claims : JWT 에서 페이로드에 담기는 정보 (사용자, 인증 정보) 객체
        Claims claims = Jwts.claims()
            .subject(String.valueOf(employee.getId()))   // id값 저장
            .build();

        // role 이 없을 경우 예외 처리
        Set<String> roleNames = new HashSet<>();
        if (employee.getRoles() != null && ! employee.getRoles().isEmpty()) {
            roleNames = employee.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        } else {
            roleNames = Collections.emptySet();
        }

        return Jwts.builder()
            .claims(claims)
            .issuedAt(now)
            .expiration(expireAt)
            .claim("nickname", employee.getKakaoNickName())
            .claim("roles", roleNames)
            .signWith(secretKey)
            .compact();
    }

    public Claims parseToken(String token) {

        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}
