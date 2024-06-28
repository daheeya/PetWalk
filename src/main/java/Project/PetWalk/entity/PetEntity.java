package Project.PetWalk.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pet")
@Entity
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50)
    private int age;

    @ManyToOne(fetch = FetchType.EAGER)   // UserEntity.idx 참조
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "petEntity", orphanRemoval = true)       //UseImgEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<PetImgEntity> petImgEntities = new ArrayList<>();
}

/*
table pet {
  idx bigint [pk, not null, increment]
  name varchar(50) [not null]
  age int
  gender varchar(10) [not null]
  image text
  weight int
  user_idx bigint [not null]
}
Ref: pet.user_idx > user.idx [delete: cascade]
 */