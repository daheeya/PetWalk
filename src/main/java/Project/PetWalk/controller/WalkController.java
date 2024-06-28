package Project.PetWalk.controller;

import Project.PetWalk.dto.PathDto;
import Project.PetWalk.dto.WalkDto;
import Project.PetWalk.dto.WalkReqDto;
import Project.PetWalk.entity.WalkEntity;
import Project.PetWalk.service.WalkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/walks")
@RequiredArgsConstructor
@Slf4j
public class WalkController {

    @GetMapping(path = "/history")
    public String walks(Model model) {
        return "WalkHistory";
    }

    @Autowired
    private WalkService walkService;

    @PostMapping("/start")
    public ResponseEntity<WalkDto> startWalk(@RequestBody WalkReqDto walkReqDto) {
        log.info("walkDto: {}", walkReqDto);
        WalkEntity walkEntity = walkService.createWalk(walkReqDto);
        log.info("walkEntity: {}", ResponseEntity.ok(walkEntity));
        WalkDto walkDto= WalkDto.builder()
                .idx(walkEntity.getIdx())
                .calories(walkEntity.getCalories())
                .distance(walkEntity.getDistance())
                .startWalkTime(walkEntity.getStartWalkTime())
                .endWalkTime(walkEntity.getEndWalkTime())
                .stepCount(walkEntity.getStepCount())
                .petIdx(walkEntity.getPetEntity().getIdx())
                .userIdx(walkEntity.getUserEntity().getIdx())
                .build();
        return ResponseEntity.ok(walkDto);
    }

    @PostMapping("/path")
    public ResponseEntity<?> savePath(@RequestBody PathDto pathDto) {
        log.info("pathDto: {}", pathDto);
        walkService.savePath(pathDto);
        return ResponseEntity.ok("Path saved successfully");
    }

    @PutMapping("/end/{id}")
    public ResponseEntity<?> endWalk(@PathVariable Long id, @RequestBody WalkDto walkDto) {
        walkService.endWalk(id, walkDto);
        return ResponseEntity.ok("Walk ended successfully");
    }
}
