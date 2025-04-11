package com.example.auth_sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class KakaoLoginController {

    @GetMapping("/kakao/callback")
    public ResponseEntity callback(@RequestParam("code") String code) {

        log.info("code " + code);   // info 레벨의 로그 생성
        return new ResponseEntity(HttpStatus.OK);
    }
}
