package Project.PetWalk.controller;

//import Project.PetWalk.config.SessionUtil;
import Project.PetWalk.dto.LoginParamsDto;
import Project.PetWalk.dto.NaverUserInfo;
import Project.PetWalk.dto.OAuthProvider;
import Project.PetWalk.entity.UserEntity;
import Project.PetWalk.service.NaverLoginService;
import Project.PetWalk.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.tags.Param;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/oauth")
@Controller
public class NaverLoginController {

    private final NaverLoginService oAuthLoginService;
    private final UserService userService;

    // HTML단에서 Naver login 버튼 선택시 적용되는 URI
    @GetMapping("/naver/login")
    public void naverLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect(oAuthLoginService.naverAuthUrl());
    }

    // Naver redirect URL
    @GetMapping(path = "/naver")
    public ResponseEntity<String> callbackNaver(LoginParamsDto loginParamsDto, HttpServletRequest request) {
        log.info("code={}, state={}", loginParamsDto.getCode(), loginParamsDto.getState());
        String token = oAuthLoginService.requestAccessToken(loginParamsDto);
        NaverUserInfo naverUserInfo = oAuthLoginService.findMe(token);

        if (naverUserInfo != null) {
            String email = naverUserInfo.getResponse().getEmail();
            boolean userExists = userService.isUserExists(email, OAuthProvider.NAVER);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/oauth/map");

            if (userExists) {
                // 유저가 이미 존재하면 유저 정보를 세션에 저장하고 바로 map 페이지로 리다이렉트
                UserEntity user = userService.findByEmailAndServiceProvider(email, OAuthProvider.NAVER).orElse(null);
                request.getSession().setAttribute("user", user);
                return new ResponseEntity<>(headers, HttpStatus.FOUND);
            } else {
                try {
                    // 유저가 존재하지 않으면 정보 저장 후 유저 정보를 세션에 저장하고 map 페이지로 리다이렉트
                    UserEntity user = userService.saveNaverUserInfo(naverUserInfo);
                    request.getSession().setAttribute("user", user);
                    return new ResponseEntity<>(headers, HttpStatus.FOUND);
                } catch (DataIntegrityViolationException e) {
                    log.error("중복된 사용자 정보로 인해 데이터베이스 삽입에 실패했습니다.", e);
                    // 이미 유저가 존재하는 것으로 간주하고 리다이렉트
                    return new ResponseEntity<>(headers, HttpStatus.FOUND);
                }
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Naver 사용자 정보를 가져오지 못했습니다.");
        }
    }
}

