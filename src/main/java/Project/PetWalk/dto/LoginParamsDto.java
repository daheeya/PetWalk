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
public class LoginParamsDto {
    private String code;
    private String state;
    private String error;
    @JsonProperty(value = "error_description")
    private String errorDescription;
}
