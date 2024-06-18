package Project.PetWalk.repository;

import Project.PetWalk.entity.WalkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalkRepository extends JpaRepository<WalkEntity, Long> {

}
