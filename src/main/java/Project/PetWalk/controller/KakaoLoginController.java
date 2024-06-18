package Project.PetWalk.controller;

import Project.PetWalk.dto.LoginParamsDto;
import Project.PetWalk.service.KakaoLoginService;
import Project.PetWalk.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity callbackKakao(LoginParamsDto loginParamsDto) {
        log.info("code={}, state={}", loginParamsDto.getCode(), loginParamsDto.getState());
        var token = kakaoLoginService.requestAccessToken(loginParamsDto);
        var kakaoUserInfo = kakaoLoginService.findMe(token);
        // Kakao 사용자 정보가 null이 아닌 경우, UserService를 통해 UserEntity로 저장합니다.
        if (kakaoUserInfo != null) {
            userService.saveKakaoUserInfo(kakaoUserInfo);
            return ResponseEntity.ok("Kakao 로그인 성공!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Kakao 사용자 정보를 가져오지 못했습니다.");
        }
//        oAuthLoginService.findMe(token);
//        return null;
    }
}