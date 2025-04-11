package com.example.auth_sample.service;

import com.example.auth_sample.controller.dto.responseDto.KakaoTokenResponseDto;
import com.example.auth_sample.controller.dto.responseDto.KakaoUserInfoResponseDto;
import io.netty.handler.codec.http.HttpHeaderValues;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KakaoService {

    private final String KAKAO_AUTH_URL = "https://kauth.kakao.com";
    private final String KAKAO_USER_URL = "https://kapi.kakao.com";

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    public String getAccessTokenFromKakao(String code) {

        KakaoTokenResponseDto kakaoTokenResponseDto =
            WebClient.create(KAKAO_AUTH_URL)
                .post()
                .uri(uriBuilder -> uriBuilder
                    .scheme("https")
                    .path("/oauth/token")
                    .queryParam("grant_type", "authorization_code")
                    .queryParam("client_id", clientId)
                    .queryParam("redirect_uri", redirectUri)
                    .queryParam("code", code)
                    .build())
                .header(HttpHeaders.CONTENT_TYPE,
                    HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();

        return kakaoTokenResponseDto.getAccessToken();
    }

    public KakaoUserInfoResponseDto getUserFromKakao(String accessToken) {

        return WebClient.create(KAKAO_USER_URL)
                .get()
                .uri(uriBuilder -> uriBuilder
                    .scheme("https")
                    .path("/v2/user/me")
                    .build())
                .header("Authorization", "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE,
                    HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();
    }
}
