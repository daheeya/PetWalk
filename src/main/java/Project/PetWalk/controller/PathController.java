package Project.PetWalk.controller;

import Project.PetWalk.dto.PathDto;
import Project.PetWalk.service.PathService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/saveLocation")
@RequiredArgsConstructor
public class PathController {

    private static final Logger log = LoggerFactory.getLogger(PathController.class);
    private final PathService pathService;

    @PostMapping
    public void PathController(@RequestBody PathDto pathDto) {
        pathService.saveLocation(pathDto);
        log.info("Saved path: {}", pathDto);
    }
}
