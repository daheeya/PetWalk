package Project.PetWalk.entity;

import Project.PetWalk.entity.listener.DefaultListener;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(value = DefaultListener.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class PostEntity implements IEntityAdapter<LocalDateTime> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime crateAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY)   // UserEntity.idx 참조
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "postEntity", orphanRemoval = true)            // CommentEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<CommentEntity> commentEntities = new ArrayList<>();

    @OneToMany(mappedBy = "postEntity", orphanRemoval = true)            // PostImgEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<PostImgEntity> postImgEntities = new ArrayList<>();

    @OneToMany(mappedBy = "postEntity", orphanRemoval = true)            // PostLikeEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<PostLikeEntity> postLikeEntities = new ArrayList<>();

}

/*
table post {
  idx bigint [pk, not null, increment]
  post_title varchar [not null]
  post_contents varchar [not null]
  post_created_at datetime [not null]
  user_idx bigint
}
Ref: post.user_idx > user.idx
 */