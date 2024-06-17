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
public class CommentDto {
    @JsonProperty("user_idx")
    private long userIdx;
    @JsonProperty("post_idx")
    private long postIdx;
    private String content;
    @JsonProperty("like_count")
    @Builder.Default
    private int likeCount=0;
}
