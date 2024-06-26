package Project.PetWalk.entity;

import Project.PetWalk.dto.OAuthProvider;
import Project.PetWalk.entity.listener.IAuditable;
import Project.PetWalk.entity.listener.UserEntityListener;
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
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "oauthProvider"})
})
@EntityListeners(value = {UserEntityListener.class})
@Entity
public class UserEntity implements IAuditable {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter
    @Getter
    @Column(length = 50, nullable = false)
    private String nickname;

    @Setter
    @Getter
    @Column(nullable = false)
    private String email;

    @Setter
    @Getter
    @Column(nullable = true)
    private String phoneNumber;


    @Setter
    @Getter
    private int point;

    @Setter
    @Getter
    @Column(nullable = true)
    private String address;

    @Setter
    @Getter
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Setter
    @Getter
    @Column(nullable = false)
    LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OAuthProvider serviceProvider;

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

    @Transient
    private boolean isValid;

    @OneToMany(mappedBy = "userEntity", orphanRemoval = true)       //WalkEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<WalkEntity> walkEntities = new ArrayList<>();
}