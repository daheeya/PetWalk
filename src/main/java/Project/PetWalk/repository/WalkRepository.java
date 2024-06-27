package Project.PetWalk.repository;

import Project.PetWalk.entity.WalkEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Repository
public interface WalkRepository extends JpaRepository<WalkEntity, Long> {

    String str = "INSERT INTO walk (distance, start_walk_time, end_walk_time, step_count, calories, path, user_idx, pet_idx) " +
            "VALUES (:distance, :startWalkTime, :endWalkTime, :stepCount, :calories, ST_GeomFromText(:path, 4326), :userIdx, :petIdx)";
    @Modifying
    @Transactional
    @Query(value = str, nativeQuery = true)
    void saveWalkNative(@Param("distance") int distance,
                        @Param("startWalkTime") LocalDateTime startWalkTime,
                        @Param("endWalkTime") LocalDateTime endWalkTime,
                        @Param("stepCount") int stepCount,
                        @Param("calories") int calories,
                        @Param("path") String path,
                        @Param("userIdx") Long userIdx,
                        @Param("petIdx") Long petIdx);
}

