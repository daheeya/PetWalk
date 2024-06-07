package Project.PetWalk.entity.listener;

import Project.PetWalk.entity.IEntityAdapter;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class DefaultListener {
    @PrePersist
    public void prePersist(Object o) {
        if (o instanceof IEntityAdapter) {
            ((IEntityAdapter) o).setCrateAt(LocalDateTime.now());
            ((IEntityAdapter) o).setUpdateAt(LocalDateTime.now());
        }
    }


    @PreUpdate
    public void preUpdate(Object o) {
        if (o instanceof IEntityAdapter) {
            ((IEntityAdapter) o).setUpdateAt(LocalDateTime.now());
        }
    }
}
