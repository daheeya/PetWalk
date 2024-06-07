package Project.PetWalk.repository;

import Project.PetWalk.entity.UserDailyMissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDailyMissionRepository extends JpaRepository<UserDailyMissionEntity, Long> {

}

