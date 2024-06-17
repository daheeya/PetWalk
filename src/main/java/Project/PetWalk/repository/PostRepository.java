package Project.PetWalk.repository;

import Project.PetWalk.dto.PostDto;
import Project.PetWalk.entity.PostEntity;
import org.springframework.data.domain.Pageable;  // 수정
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
