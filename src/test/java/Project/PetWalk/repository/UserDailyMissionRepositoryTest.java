package Project.PetWalk.repository;

import Project.PetWalk.entity.DailyMissionEntity;
import Project.PetWalk.entity.UserDailyMissionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDailyMissionRepositoryTest {

    @Autowired
    UserDailyMissionRepository userDailyMissionRepository;

    @Autowired
    DailyMissionRepository dailyMissionRepository;

    @Test
    void addUserDailyMission() {

        DailyMissionEntity savedDailyMission = dailyMissionRepository.save(DailyMissionEntity.builder()
                .name("Test1")
                .description("test111")
                .status(true)
                .createdAt(LocalDateTime.now())
                .build());

        UserDailyMissionEntity savedUserDailyMission = userDailyMissionRepository.save(UserDailyMissionEntity.builder()
                .dailyMissionEntity(savedDailyMission)
                .assignedAt(LocalDateTime.now())
                .build());

        System.out.println("Saved UserDailyMission: " + savedUserDailyMission);
    }
}