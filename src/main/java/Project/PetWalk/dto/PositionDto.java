package Project.PetWalk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PositionDto {
    private String latitude; // 위도
    private String longitude;  // 경도
}
