package Project.PetWalk.service;

import Project.PetWalk.entity.WalkEntity;
import Project.PetWalk.repository.WalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class WalkService {

    @Autowired
    private WalkRepository walkRepository;

    public void startWalk(){
        LocalDateTime startWalkTime = LocalDateTime.now();
        Long userIdx = 1L; // 실제 사용자 ID로 교체
        Long petIdx = 1L;  // 실제 애완동물 ID로 교체

    }

    @Transactional
    public void saveWalk(WalkEntity walkEntity) {  // 산책 종료 시 데이터베이스에 저장

        walkRepository.saveWalkNative(walkEntity.getDistance()
                , walkEntity.getStartWalkTime()
                , walkEntity.getEndWalkTime()
                , walkEntity.getStepCount()
                , walkEntity.getCalories()
                , walkEntity.getPath().toString()
                , walkEntity.getUserEntity().getIdx()
                , walkEntity.getPetEntity().getIdx());
    }
}