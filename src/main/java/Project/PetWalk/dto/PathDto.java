package Project.PetWalk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PathDto {
    private double latitude;
    private double longitude;
    private long walkIdx;
}
