package Project.PetWalk.repository;

import Project.PetWalk.entity.PetEntity;
import Project.PetWalk.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PetRepository petRepository;

    @Test
    @Transactional
    @Rollback(false)
    void test() {
        var pet= PetEntity.builder().idx(1L)
                .age(1)
                .name("koko")
                .userEntity(userRepository.findById(2L).get())
                .build();
        petRepository.save(pet);
    }
}