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
@Table(name = "comment")
@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)       // PostEntity.idx 를 참조
    @JoinColumn(name = "post_idx")
    private PostEntity postEntity;

    @ManyToOne(fetch = FetchType.LAZY)       // User.idx 를 참조
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "commentEntity", orphanRemoval = true)            // CommentLikeEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<CommentLikeEntity> commentLikeEntities = new ArrayList<>();
}

/*
table comment {
	idx bigint [pk, not null, increment]
	comment_content varchar
	comment_created_at datetime
  post_idx bigint
  user_idx bigint
}
Ref: comment.post_idx > post.idx [delete: cascade]
Ref: comment.user_idx > user.idx
 */
