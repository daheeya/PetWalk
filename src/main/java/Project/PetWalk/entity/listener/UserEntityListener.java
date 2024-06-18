package Project.PetWalk.entity.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class UserEntityListener {
    @PrePersist
    public void prePersist(Object o) {
        var obj = (IAuditable) o;
        obj.setCreatedAt(LocalDateTime.now());
        obj.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(Object o) {
        var obj = (IAuditable) o;
        obj.setUpdatedAt(LocalDateTime.now());
    }
}
