package Project.PetWalk.repository;

import Project.PetWalk.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(value = false)
   void test(){
       var user1 = UserEntity.builder().idx(1L).name("cooper").age(23)
               .address("daegu").email("cooper091677@gmail.com").phoneNumber("010-1234-5678").nickName("redia").build();
        System.out.println();
       userRepository.save(user1);
   }

}