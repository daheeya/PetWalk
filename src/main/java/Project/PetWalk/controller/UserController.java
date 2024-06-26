package Project.PetWalk.controller;

import Project.PetWalk.dto.KakaoUserInfo;
import Project.PetWalk.dto.LoginParamsDto;
import Project.PetWalk.dto.NaverUserInfo;
import Project.PetWalk.entity.UserEntity;
import Project.PetWalk.service.KakaoLoginService;
import Project.PetWalk.service.NaverLoginService;
import Project.PetWalk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Slf4j
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    private final UserService userService;
//    private final NaverLoginService naverLoginService;
//
//    @Autowired
//    public UserController(UserService userService, NaverLoginService naverLoginService) {
//        this.userService = userService;
//        this.naverLoginService = naverLoginService;
//    }
//
//    @GetMapping
//    public List<UserEntity> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//
//    @GetMapping("/naver/callback")
//    public ResponseEntity<String> naverCallback(@RequestParam String code, @RequestParam String state) {
//        LoginParamsDto loginParamsDto = new LoginParamsDto();
//        loginParamsDto.setCode(code);
//        loginParamsDto.setState(state);
//        String accessToken = naverLoginService.requestAccessToken(loginParamsDto);
//        NaverUserInfo naverUserInfo = naverLoginService.findMe(accessToken);
//        return ResponseEntity.ok("Naver login successful!");
//    }
//}