package Project.PetWalk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostLikeDto {
    @JsonProperty("user_idx")
    long userIdx;
    @JsonProperty("post_idx")
    long postIdx;
}
