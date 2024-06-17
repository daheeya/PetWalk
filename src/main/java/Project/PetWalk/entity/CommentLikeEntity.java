package Project.PetWalk.entity;

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
@Table(name = "comment_like")
@Entity
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)   // UserEntity.idx 참조
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)  //CommentEntity.idx 를 참조하고 있음
    @JoinColumn(name = "comment_idx")
    private CommentEntity commentEntity;
}
