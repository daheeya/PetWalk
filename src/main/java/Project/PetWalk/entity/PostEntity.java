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
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Setter
    @Getter
    @Column(length = 50, nullable = false)
    private String title;

    @Setter
    @Getter
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Setter
    @Getter
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Setter
    @Getter
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Setter
    @Getter
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
    // 이미지 추가
    public void addImg (PostImgEntity postImgEntity){
        postImgEntities.add(postImgEntity);
    }

    @OneToMany(mappedBy = "postEntity", orphanRemoval = true)            // PostLikeEntity 에서 참조하고 있음
    @ToString.Exclude
    @Builder.Default
    private List<PostLikeEntity> postLikeEntities = new ArrayList<>();

    // 좋아요 추가
    public void addLike(PostLikeEntity postLikeEntity){
        postLikeEntities.add(postLikeEntity);
    }

    @OneToMany(mappedBy = "postEntity")
    @ToString.Exclude
    @Builder.Default
    private List<TagEntity> tagEntities = new ArrayList<>();
    // tag 추가
    public void addTag(TagEntity tagEntity){
        tagEntities.add(tagEntity);
    }

}
