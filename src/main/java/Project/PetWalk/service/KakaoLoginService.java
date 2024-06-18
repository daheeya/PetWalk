package Project.PetWalk.service;

import Project.PetWalk.dto.KakaoTokenDto;
import Project.PetWalk.dto.KakaoUserInfo;
import Project.PetWalk.dto.LoginParamsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class KakaoLoginService {

    @Value("${kakao.uri.auth}")
    private String authUrl;
    @Value("${kakao.redirect-url}")
    private String redirectUrl;
    @Value("${kakao.client-id}")
    private String clientId;

    private final RestTemplate restTemplate;


    public KakaoLoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String kakaoAuthUrl() {
        return UriComponentsBuilder.fromUriString(authUrl + "/oauth/authorize")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUrl)
                .queryParam("response_type", "code")
                .encode()
                .build().toUriString();
    }


    public String requestAccessToken(LoginParamsDto dto) {
        var uri = "https://kauth.kakao.com/oauth/token";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUrl);
        params.add("code", dto.getCode());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", "application/json");

        HttpEntity entity = new HttpEntity<>(params, headers);
        var response = restTemplate.postForObject(uri, entity, KakaoTokenDto.class);

        log.info("Access token: {}", response.getAccessToken()); // 디버깅을 위한 로그 추가
        return response.getAccessToken();
    }


//    public KakaoUserInfo findMe(String accessToken) {
//        String keys = "[\"kakao_account.profile\",\"kakao_account.email\"]";
//        var uri = UriComponentsBuilder.fromUriString("https://kapi.kakao.com/v2/user/me")
//                .queryParam("property_keys", keys)
//                .encode()
//                .build().toUri();
//
//
//        var requestEntity = RequestEntity.get(uri)
//                .header("Authorization", "Bearer " + accessToken)
//                .build();
//
//        var res = restTemplate.exchange(requestEntity, KakaoUserInfo.class);
//
//        return res.getBody();
//    }

    public KakaoUserInfo findMe(String accessToken) {
        String keys = "[\"kakao_account.profile\",\"kakao_account.email\"]";
        var uri = UriComponentsBuilder.fromUriString("https://kapi.kakao.com/v2/user/me")
                .queryParam("property_keys", keys)
                .encode()
                .build().toUri();

        var requestEntity = RequestEntity.get(uri)
                .header("Authorization", "Bearer " + accessToken)
                .build();

        var res = restTemplate.exchange(requestEntity, KakaoUserInfo.class);
        log.info("Kakao user info: {}", res.getBody()); // 디버깅을 위한 로그 추가
        return res.getBody();
    }
}