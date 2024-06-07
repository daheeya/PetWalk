package Project.PetWalk.utils.Naverdto;

import lombok.Data;

@Data
public class NaverTokenResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private Integer expires_in;
    private String scope;
    private Integer refresh_token_expires_in;

}