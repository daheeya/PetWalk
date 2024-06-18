package Project.PetWalk.controller;

import Project.PetWalk.dto.LoginParamsDto;
import Project.PetWalk.service.NaverLoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    // html단에서 naver login 버튼 선택시 적용되는 URI
    @GetMapping("/naver/login")
    public void naverLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect(oAuthLoginService.naverAuthUrl());
    }

    // naver redirect url
    @GetMapping(path = "/naver")
    public ResponseEntity callbackNaver(LoginParamsDto loginParamsDto) {
        log.info("code={}, state={}", loginParamsDto.getCode(), loginParamsDto.getState());
        var token = oAuthLoginService.requestAccessToken(loginParamsDto);

        //FindMeDto.Response res = oAuthLoginService.findMe(token).getResponse();
        // service단에서 repository 주입받아서 EMAIL,NICKNAME 저장
//        userService.save(UserDto.builder().name(res.getNickname()).email(res.getEmail()).build());
        return ResponseEntity.ok(oAuthLoginService.findMe(token));

        //return ResponseEntity.ok("test");
    }
}
