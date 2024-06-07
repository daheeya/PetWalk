package Project.PetWalk.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private int age;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String nickName;

    private int point;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column(nullable = false)
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)      //PostEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<PostEntity> postEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)       //CommentEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<CommentEntity> commentEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)       //CommentEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<PetEntity> petEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)       //UseImgEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<UserImgEntity> userImgEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)       //CommentLikeEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<CommentLikeEntity> commentLikeEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)       //PostLikeEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<PostLikeEntity> postLikeEntities = new ArrayList<>();

    @Builder
    public UserEntity(Long id, String email) {
        this.idx = id;
        this.email = email;
    }
}

/*
table user {
  idx bigint [pk, not null, increment]
  name varchar(50) [not null]
  age int [not null]
  email varchar(100) [not null, unique]
  phone_number varchar(50) [not null, unique]
  nickname varchar(50)
  point int
  address varchar(100) [not null]
}
 */