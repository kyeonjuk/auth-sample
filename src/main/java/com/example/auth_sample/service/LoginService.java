package com.example.auth_sample.service;

import com.example.auth_sample.controller.dto.responseDto.KakaoUserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final KakaoService kakaoService;
    private final EmployeeService employeeService;

    public ResponseEntity login(String code) {
        String token = kakaoService.getAccessTokenFromKakao(code);
        KakaoUserInfoResponseDto dto = kakaoService.getUserFromKakao(token);

        String nickName = dto.getKakaoAccount().getProfile().getNickName();

        // 인사팀에서 등록시킨 회원만 카카오 로그인 가능
        if (employeeService.existsByKakaoNickName(nickName)) {
            return new ResponseEntity("환영합니다. " + nickName + "님" + dto, HttpStatus.OK);
        } else {
            return new ResponseEntity("", HttpStatus.FORBIDDEN);
        }
    }
}
