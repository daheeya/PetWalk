package Project.PetWalk.repository;

import Project.PetWalk.entity.UserImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImgRepository extends JpaRepository<UserImgEntity, Long> {
}
