package Project.PetWalk.api;

import Project.PetWalk.config.UserService;
import Project.PetWalk.utils.KaKaodto.KakaoTokenJsonData;
import Project.PetWalk.utils.KaKaodto.KakaoTokenResponse;
import Project.PetWalk.utils.KaKaodto.KakaoUserInfoResponse;
import Project.PetWalk.utils.KakaoUserInfo;
import Project.PetWalk.utils.NaverUserInfo;
import Project.PetWalk.utils.Naverdto.NaverTokenJsonData;
import Project.PetWalk.utils.Naverdto.NaverTokenResponse;
import Project.PetWalk.utils.Naverdto.NaverUserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class oauthController {
    private final KakaoTokenJsonData kakaoTokenJsonData;
    private final KakaoUserInfo kakaoUserInfo;
    private final NaverTokenJsonData naverTokenJsonData;
    private final NaverUserInfo naverUserInfo;
    private final UserService userService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/oauth/kakao")
    @ResponseBody
    public String kakaoOauth(@RequestParam("code") String code) {
        KakaoTokenResponse kakaoTokenResponse = kakaoTokenJsonData.getToken(code);
        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(kakaoTokenResponse.getAccess_token());

        userService.createUser(userInfo.getKakao_account().getEmail());

        return "okay";
    }

    @GetMapping("/oauth/naver")
    @ResponseBody
    public String naverOauth(@RequestParam("code") String code) {
        NaverTokenResponse naverTokenResponse = naverTokenJsonData.getToken(code);
        NaverUserInfoResponse userInfo = naverUserInfo.getUserInfo(naverTokenResponse.getAccess_token());

        String email = userInfo.getEmail();
        userService.createUser(email);
        return "okay";
    }
}
