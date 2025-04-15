package com.example.auth_sample.util;

import static org.junit.jupiter.api.Assertions.*;

import com.example.auth_sample.model.Employee;
import com.example.auth_sample.model.Role;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.lang.reflect.Field;
import java.security.Key;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JwtUtilTest {

    private static Employee employee;
    private static String testNick;
    private final JwtUtil jwtUtil = new JwtUtil("your-test-secret-key-which-is-long-enough");

    @BeforeAll
    static void init() {
        testNick = "john doe";
        employee = Employee.builder()
            .kakaoNickName(testNick)
            .build();
    }


    @Test
    void givenCreatedToken_whenParseToken_thenGetNickname() {
        // given
        String token = jwtUtil.createToken(employee);

        // when, then
        assertEquals(testNick, jwtUtil.parseToken(token).get("nickname"));
    }

    @Test
    void givenCreatedToken_whenParseToken_thenGetRoles() {
        // given
        Role role1 = Role.builder()
            .id(1L)
            .name("role1")
            .build();

        Role role2 = Role.builder()
            .id(2L)
            .name("role2")
            .build();

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role1);
        roleSet.add(role2);

        Employee employee = Employee.builder()
            .roles(roleSet)
            .build();

        String token = jwtUtil.createToken(employee);

        // JWT가 Set을 List로 자동변환하기 때문에
        List response = jwtUtil.parseToken(token).get("roles", List.class);

        // when, then
        assertEquals(roleSet.size(), response.size());
        assertTrue(response.contains(role1.getName()));
        assertTrue(response.contains(role2.getName()));
    }
}