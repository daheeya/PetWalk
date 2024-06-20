package Project.PetWalk.controller;

import Project.PetWalk.dto.KakaoUserInfo;
import Project.PetWalk.dto.LoginParamsDto;
import Project.PetWalk.service.KakaoLoginService;
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
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;
    private final UserService userService;

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    // html단에서 kakao login 버튼 선택시 적용되는 URI
    @GetMapping("/kakao/login")
    public void kakaoLogin(HttpServletResponse response) throws IOException {
        var kakao = kakaoLoginService.kakaoAuthUrl();
        System.out.println(kakao);
        response.sendRedirect(kakaoLoginService.kakaoAuthUrl());
    }
    // Kakao redirect URL

    @GetMapping(path = "/kakao")
    public ResponseEntity<String> callbackKakao(LoginParamsDto loginParamsDto) {
        log.info("code={}, state={}", loginParamsDto.getCode(), loginParamsDto.getState());
        String token = kakaoLoginService.requestAccessToken(loginParamsDto);
        KakaoUserInfo kakaoUserInfo = kakaoLoginService.findMe(token);

        if (kakaoUserInfo != null) {
            String email = kakaoUserInfo.getKakaoAccount().getEmail();
            if (userService.isUserExists(email)) {
                // 유저가 이미 존재하면 바로 map 페이지로 리다이렉트
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/oauth/map");
                return new ResponseEntity<>(headers, HttpStatus.FOUND);
            } else {
                // 유저가 존재하지 않으면 정보 저장 후 map 페이지로 리다이렉트
                userService.saveKakaoUserInfo(kakaoUserInfo);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/oauth/map");
                return new ResponseEntity<>(headers, HttpStatus.FOUND);
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Kakao 사용자 정보를 가져오지 못했습니다.");
        }
    }

    @GetMapping("/map")
    public String mainPage() {
        return "walk/map";
    }
}