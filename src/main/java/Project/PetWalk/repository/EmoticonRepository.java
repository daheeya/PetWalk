package Project.PetWalk.repository;

import Project.PetWalk.entity.EmoticonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmoticonRepository extends JpaRepository<EmoticonEntity, Long> {
    Optional<EmoticonEntity> findByName(String name);
}