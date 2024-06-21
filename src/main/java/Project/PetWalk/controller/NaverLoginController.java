package Project.PetWalk.controller;

import Project.PetWalk.dto.LoginParamsDto;
import Project.PetWalk.dto.NaverUserInfo;
import Project.PetWalk.dto.OAuthProvider;
import Project.PetWalk.service.NaverLoginService;
import Project.PetWalk.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/oauth")
@Controller
public class NaverLoginController {

    private final NaverLoginService oAuthLoginService;
    private final UserService userService;

    // html단에서 naver login 버튼 선택시 적용되는 URI
    @GetMapping("/naver/login")
    public void naverLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect(oAuthLoginService.naverAuthUrl());
    }

    // naver redirect url
    @GetMapping(path = "/naver")
    public ResponseEntity<String> callbackNaver(LoginParamsDto loginParamsDto) {
        log.info("code={}, state={}", loginParamsDto.getCode(), loginParamsDto.getState());
        String token = oAuthLoginService.requestAccessToken(loginParamsDto);
        NaverUserInfo naverUserInfo = oAuthLoginService.findMe(token);

        if (naverUserInfo != null) {
            String email = naverUserInfo.getResponse().getEmail();
            if (userService.isUserExists(email, OAuthProvider.NAVER)) {
                // 유저가 이미 존재하면 바로 map 페이지로 리다이렉트
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/oauth/map");
                return new ResponseEntity<>(headers, HttpStatus.FOUND);
            } else {
                // 유저가 존재하지 않으면 정보 저장 후 map 페이지로 리다이렉트
                userService.saveNaverUserInfo(naverUserInfo);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/oauth/map");
                return new ResponseEntity<>(headers, HttpStatus.FOUND);
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Naver 사용자 정보를 가져오지 못했습니다.");
        }
    }
}
