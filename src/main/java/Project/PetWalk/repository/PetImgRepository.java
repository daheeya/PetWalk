package Project.PetWalk.repository;

import Project.PetWalk.entity.PetImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetImgRepository extends JpaRepository<PetImgEntity, Long> {
}
