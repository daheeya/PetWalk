package Project.PetWalk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "emoticon")
@Entity

public class EmoticonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private List<String> image;
}

/*
커밋 테스트 아아 보이십니까?
 */





/*
----------------------------------------------------------------------------------------------\

table emoticon {
	idx bigint [pk, not null, increment]
	name varchar [not null]
  image list
}
 */