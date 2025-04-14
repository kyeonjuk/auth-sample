package com.example.auth_sample.controller;

import com.example.auth_sample.controller.dto.responseDto.KakaoUserInfoResponseDto;
import com.example.auth_sample.service.EmployeeService;
import com.example.auth_sample.service.KakaoService;
import com.example.auth_sample.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class KakaoLoginController {

    private final LoginService loginService;

    @GetMapping("/kakao/callback")
    public ResponseEntity callback(@RequestParam("code") String code) {
        return loginService.login(code);
    }
}
