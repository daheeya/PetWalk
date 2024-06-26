package Project.PetWalk.dto;

import lombok.*;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    // Getters and Setters
    private Long idx;
    private String username;
    private String email;
    private String phoneNumber;

}
