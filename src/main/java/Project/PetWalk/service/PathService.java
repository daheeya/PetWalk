package Project.PetWalk.service;

import Project.PetWalk.dto.PathDto;
import Project.PetWalk.entity.PathEntity;
import Project.PetWalk.repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathService {
    @Autowired
    private PathRepository pathRepository;

    public void saveLocation(PathDto pathDto) {
        PathEntity pathEntity = new PathEntity();
        pathEntity.setLatitude(pathDto.getLatitude());
        pathEntity.setLongitude(pathDto.getLongitude());
        pathRepository.save(pathEntity);
    }
}
