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
@Table(name = "post_like")
@Entity
public class PostLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)   // UserEntity.idx 참조
    @JoinColumn(name = "user_idx")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)  //PostEntity.idx 를 참조하고 있음
    @JoinColumn(name = "post_idx")
    private PostEntity postEntity;
}

/*
table like {
  idx bigint [pk, not null, increment]
  user_idx bigint
  post_idx bigint
}
Ref: like.user_idx > user.idx
Ref: like.post_idx > post.idx [delete: cascade]
 */