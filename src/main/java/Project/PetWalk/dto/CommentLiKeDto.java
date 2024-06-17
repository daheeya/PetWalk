package Project.PetWalk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentLiKeDto {
    @JsonProperty("user_idx")
    private long userIdx;
    @JsonProperty("comment_idx")
    private long commentIdx;
}
