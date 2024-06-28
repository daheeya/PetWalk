package Project.PetWalk.service;

import Project.PetWalk.repository.WalkRepository;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WalkServiceTest {

    @Autowired
    private WalkRepository walkRepository;

    private Point createPoint(double latitude, double longitude) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }

    private MultiPoint createPath() {
        Point[] points = new Point[2];
        points[0] = createPoint(35.89769, 15.85716);
        points[1] = createPoint(35.8997169, 12.8507106);

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

        MultiPoint multiPoint = geometryFactory.createMultiPoint(points);

        return multiPoint;
    }

    @Test
    void saveWalk() {
        // 데이터 설정
        int distance = 1000;
        LocalDateTime startWalkTime = LocalDateTime.now();
        LocalDateTime endWalkTime = LocalDateTime.now().plusHours(1);
        int stepCount = 1000;
        int calories = 100;
        Long userIdx = 1L; // 실제 사용자 ID로 교체
        Long petIdx = 1L;  // 실제 애완동물 ID로 교체

        // 네이티브 쿼리 메서드 호출
        //walkRepository.saveWalkNative(distance, startWalkTime, endWalkTime, stepCount, calories, userIdx, petIdx);

    }
}