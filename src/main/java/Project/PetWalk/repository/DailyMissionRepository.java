package Project.PetWalk.repository;

import Project.PetWalk.entity.DailyMissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyMissionRepository extends JpaRepository<DailyMissionEntity, Long> {

}

