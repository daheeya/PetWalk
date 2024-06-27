package Project.PetWalk.entity;

import Project.PetWalk.entity.listener.DefaultListener;
import Project.PetWalk.entity.listener.UserEntityListener;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


import java.time.LocalDateTime;

import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.MultiPoint;
import org.springframework.data.annotation.CreatedDate;

@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(value = {DefaultListener.class})
@Table(name = "walk")
@Entity
public class WalkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name= "distance")
    private int distance;

    @Column(name = "start_walk_time" , nullable = true)
    @Setter
    @Getter
    @CreatedDate
    private LocalDateTime startWalkTime;

    @Column(name = "end_walk_time", nullable = true)
    @Setter
    @Getter
    @CreatedDate
    private LocalDateTime endWalkTime;

    @Column(name = "step_count" , nullable = false)
    private int stepCount;

    @Column
    private int calories;

    @Column(columnDefinition = "MULTIPOINT")
    private MultiPoint path;  // TYPENAME_MULTIPOINT

    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_idx")
    private PetEntity petEntity;
}
