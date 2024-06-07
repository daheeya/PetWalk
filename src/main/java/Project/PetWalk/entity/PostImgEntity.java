package Project.PetWalk.entity;

import Project.PetWalk.entity.listener.DefaultListener;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(value = DefaultListener.class)
@Data
@SuperBuilder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post_img")
public class PostImgEntity extends BaseImgEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)  //PostEntity.idx 를 참조하고 있음
    @JoinColumn(name = "post_idx")
    private PostEntity postEntity;
}

