package Project.PetWalk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long idx;
    private Long postIdx;
    private Long userIdx;
    private String content;
    private String createdAt;
    @JsonProperty("like_count")
    @Builder.Default
    private int likeCount=0;
}
