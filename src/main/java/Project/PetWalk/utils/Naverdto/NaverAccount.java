package Project.PetWalk.utils.Naverdto;

import lombok.Data;

@Data
public class NaverAccount {
    private Boolean has_email;
    private Boolean email_needs_agreement;
    private Boolean is_email_valid;
    private Boolean is_email_verified;
    private String email;
}