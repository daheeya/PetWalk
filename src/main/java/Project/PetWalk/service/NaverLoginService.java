package Project.PetWalk.service;

import Project.PetWalk.dto.LoginParamsDto;
import Project.PetWalk.dto.NaverTokenDto;
import Project.PetWalk.dto.NaverUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Service
public class NaverLoginService {

    @Value("${naver.uri.auth}")
    private String authUrl;
    @Value("${naver.redirect-url}")
    private String redirectUrl;
    @Value("${naver.id}")
    private String clientId;
    @Value("${naver.secret}")
    private String clientSecret;

    @Value("${naver.uri.api}")
    private String openApi;

    private final RestTemplate restTemplate;
    private final UserService userService;

    public String naverAuthUrl() {
        return UriComponentsBuilder.fromUriString(authUrl+"/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUrl)
                .queryParam("state", "state string")
                .encode()
                .build().toUri().toString();
    }

    public String requestAccessToken(LoginParamsDto loginParamsDto) {
        var uri = UriComponentsBuilder.fromUriString(authUrl+"/token")
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("code", loginParamsDto.getCode())
                .queryParam("state", loginParamsDto.getState())
                .encode()
                .build().toUri();

        var requestEntity = RequestEntity.get(uri)
                .build();
        var res = restTemplate.exchange(requestEntity, NaverTokenDto.class);
        return res.getBody().getAccessToken();

    }


    //    public NaverUserInfo findMe(String accessToken) {
//        var uri = UriComponentsBuilder.fromUriString(openApi+"/v1/nid/me")
//                .encode()
//                .build().toUri();
//        log.info("uri {}", uri);
//
//        var requestEntity = RequestEntity.get(uri)
//                .header("Authorization", "Bearer "+accessToken)
//                .build();
//
//        var res = restTemplate.exchange(requestEntity, NaverUserInfo.class);
//
//        var result = res.getBody();
//        log.info("res {}", result);
//
//        return result;
//
//    }
    public NaverUserInfo findMe(String accessToken) {
        var uri = UriComponentsBuilder.fromUriString(openApi + "/v1/nid/me")
                .encode()
                .build().toUri();
        log.info("uri {}", uri);

        var requestEntity = RequestEntity.get(uri)
                .header("Authorization", "Bearer " + accessToken)
                .build();

        var res = restTemplate.exchange(requestEntity, NaverUserInfo.class);

        var result = res.getBody();
        log.info("res {}", result);

        // 유저 정보를 저장하는 로직 추가
        if (result != null && "00".equals(result.getResultcode())) {
            userService.saveNaverUserInfo(result);
        }

        return result;
    }
}

