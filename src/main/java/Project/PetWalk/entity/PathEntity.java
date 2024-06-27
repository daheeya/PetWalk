package Project.PetWalk.entity;

import Project.PetWalk.entity.listener.DefaultListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "path")
@Entity
public class PathEntity {
    @Id
    private long idx;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "walk_idx")
    private WalkEntity walkEntity;
}
