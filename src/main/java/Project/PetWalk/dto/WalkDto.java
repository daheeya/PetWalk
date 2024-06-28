package Project.PetWalk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalkDto {
    private long idx;
    private Double calories;
    private Double distance;
    private LocalDateTime startWalkTime;
    private LocalDateTime endWalkTime;
    private Integer stepCount;
    private Long petIdx;
    private Long userIdx;
}
