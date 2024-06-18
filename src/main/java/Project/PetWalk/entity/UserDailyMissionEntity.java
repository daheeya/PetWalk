package Project.PetWalk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Builder
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user_daily_mission")
@Entity
public class UserDailyMissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_idx", nullable = false)
//    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_mission_idx", nullable = false)
    private DailyMissionEntity dailyMissionEntity;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime assignedAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime completedAt;

    @PrePersist
    public void prePersist() {
        if (isCompleted() && this.completedAt == null) {
            this.completedAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void updateCompletedAt() {
        if (this.completedAt == null && isCompleted()) {
            this.completedAt = LocalDateTime.now();
        }
    }

    private boolean isCompleted() {
        return this.dailyMissionEntity != null && this.dailyMissionEntity.getStatus() != null && this.dailyMissionEntity.getStatus();
    }
}