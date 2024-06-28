package Project.PetWalk.service;

import Project.PetWalk.dto.PathDto;
import Project.PetWalk.dto.WalkDto;
import Project.PetWalk.dto.WalkReqDto;
import Project.PetWalk.entity.PathEntity;
import Project.PetWalk.entity.PetEntity;
import Project.PetWalk.entity.UserEntity;
import Project.PetWalk.entity.WalkEntity;
import Project.PetWalk.repository.PathRepository;
import Project.PetWalk.repository.PetRepository;
import Project.PetWalk.repository.UserRepository;
import Project.PetWalk.repository.WalkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class WalkService {

    private static final Logger log = LoggerFactory.getLogger(WalkService.class);
    @Autowired
    private WalkRepository walkRepository;

    @Autowired
    private PathRepository pathRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    public WalkEntity startWalk(WalkDto walkDto) {
        WalkEntity walk = new WalkEntity();
        // WalkEntity 초기화 (walkDto의 값을 설정)
        walk.setCalories(walkDto.getCalories());
        walk.setDistance(walkDto.getDistance());
        walk.setStartWalkTime(walkDto.getStartWalkTime());
        walk.setStepCount(walkDto.getStepCount());
        walk.setUserEntity(userRepository.findById(walkDto.getUserIdx()).orElseThrow(() -> new RuntimeException("User not found")));
        walk.setPetEntity(petRepository.findById(walkDto.getPetIdx()).orElseThrow(() -> new RuntimeException("Pet not found")));
        return walkRepository.save(walk);
    }

    /*
    @Transactional
    public void saveWalk(WalkEntity walkEntity) {  // 산책 종료 시 데이터베이스에 저장

        walkRepository.saveWalkNative(walkEntity.getDistance()
                , walkEntity.getStartWalkTime()
                , walkEntity.getEndWalkTime()
                , walkEntity.getStepCount()
                , walkEntity.getCalories()
                , walkEntity.getUserEntity().getIdx()
                , walkEntity.getPetEntity().getIdx());
    }*/

    public WalkEntity createWalk(WalkReqDto walkDto) {
        UserEntity userEntity = userRepository.findById(walkDto.getUserIdx())
                .orElseThrow(() -> new RuntimeException("User not found"));
        PetEntity petEntity = petRepository.findById(walkDto.getPetIdx())
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        WalkEntity walkEntity = WalkEntity.builder()
                .calories(walkDto.getCalories())
                .distance(walkDto.getDistance())
                .startWalkTime(walkDto.getStartWalkTime())
                .stepCount(walkDto.getStepCount())
                .userEntity(userEntity)
                .petEntity(petEntity)
                .build();
        log.info("Created walk entity: {}", walkEntity);
        return walkRepository.save(walkEntity);
    }

    public void savePath(PathDto pathDto) {
        WalkEntity walkEntity = walkRepository.findById(pathDto.getWalkIdx())
                .orElseThrow(() -> new RuntimeException("Walk not found"));

        PathEntity pathEntity = PathEntity.builder()
                .latitude(pathDto.getLatitude())
                .longitude(pathDto.getLongitude())
                .walkEntity(walkEntity)
                .build();
        log.info("pathEntity = {}", pathEntity);
        pathRepository.save(pathEntity);
    }

    public void endWalk(Long id, WalkDto walkDto) {
        WalkEntity walkEntity = walkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Walk not found"));

        walkEntity.setCalories(walkDto.getCalories());
        walkEntity.setDistance(walkDto.getDistance());
        walkEntity.setEndWalkTime(walkDto.getEndWalkTime());
        walkEntity.setStepCount(walkDto.getStepCount());

        walkRepository.save(walkEntity);
    }
}