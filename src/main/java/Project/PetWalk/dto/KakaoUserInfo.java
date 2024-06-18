package Project.PetWalk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class KakaoUserInfo {

    @JsonProperty(value = "kakao_account")
    private KakaoAccount kakaoAccount;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class KakaoAccount {
        private KakaoProfile profile;
        private String email;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class KakaoProfile {
        private String nickname;
    }
}

