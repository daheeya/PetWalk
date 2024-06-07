package Project.PetWalk.utils;

import Project.PetWalk.utils.Naverdto.NaverUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Component
public class NaverUserInfo {
    private final WebClient webClient;
    private static final String USER_INFO_URI = "https://openapi.naver.com/v1/nid/me";

    public NaverUserInfoResponse getUserInfo(String token) {
        String uri = USER_INFO_URI;

        Flux<NaverUserInfoResponse> response = webClient.get()
                .uri(uri)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToFlux(NaverUserInfoResponse.class);

        return response.blockFirst();
    }
}
