package Project.PetWalk.utils.Naverdto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class NaverTokenJsonData {

    private final WebClient webClient;
    private static final String TOKEN_URI = "https://nid.naver.com/oauth2.0/token";
    private static final String CLIENT_ID = "GAXhZuC_2gN1thw8fw6X";
    private static final String REDIRECT_URI = "http://localhost:8080/oauth/naver";

    public NaverTokenResponse getToken(String code) {
        String uri = TOKEN_URI + "?client_id=" + CLIENT_ID + "&REDIRECT_URI=" + REDIRECT_URI + "&code=" + code;
        System.out.println(uri);

        Flux<NaverTokenResponse> response = webClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(NaverTokenResponse.class);

        return response.blockFirst();
    }
}

