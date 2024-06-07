package Project.PetWalk.utils.Naverdto;

import lombok.Data;

@Data
public class NaverUserInfoResponse {
    private Long id;
    private String connected_at;
    private NaverAccount Naver_account;
    private String email;
    public String getEmail() {
        return email;
    }
}
