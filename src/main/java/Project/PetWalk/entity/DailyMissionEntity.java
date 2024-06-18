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
@Table(name = "daily_mission")
@Entity
public class DailyMissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean status;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime completedAt;

    @PreUpdate
    public void updateCompletedAt() {
        if (this.status != null && this.status && this.completedAt == null) {
            this.completedAt = LocalDateTime.now();
        }
    }

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = false;
        } else if (this.status && this.completedAt == null) {
            this.completedAt = LocalDateTime.now();
        }
    }
}
