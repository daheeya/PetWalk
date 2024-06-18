package Project.PetWalk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String createAt;

    @Setter
    @JsonProperty("user_idx")
    private Long userIdx;
    @Setter
    private String title;
    @Setter
    private String content;

    @Setter
    @JsonProperty("tag_list")
    @Builder.Default
    private List<String> tagList = new ArrayList<>();

    @Setter
    @JsonProperty("post_img_list")
    @Builder.Default
    private List<String> postImgList = new ArrayList<>();

    @Setter
    @JsonProperty("like_count")
    @Builder.Default
    private int likeCount = 0;

    public PostDto(long idx, String title, LocalDateTime createAt) {
        this.userIdx = idx;
        this.title = title;
        this.createAt = String.valueOf(createAt);
    }
}
