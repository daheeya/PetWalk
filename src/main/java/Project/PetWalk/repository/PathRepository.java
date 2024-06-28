package Project.PetWalk.repository;

import Project.PetWalk.entity.PathEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathRepository extends JpaRepository<PathEntity, Long> {
}
