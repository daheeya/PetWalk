package Project.PetWalk.entity.listener;

import java.time.LocalDateTime;

public interface IAuditable {
    void setCreatedAt(LocalDateTime createdAt);
    void setUpdatedAt(LocalDateTime updatedAt);

}
