package Project.PetWalk.repository;

import Project.PetWalk.entity.PostImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImgRepository extends JpaRepository<PostImgEntity, Long> {
}
