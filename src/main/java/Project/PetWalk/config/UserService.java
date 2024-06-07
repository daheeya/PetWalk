package Project.PetWalk.config;

import Project.PetWalk.entity.UserEntity;
import Project.PetWalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long createUser(String email) {
        UserEntity user = UserEntity.builder()
                .email(email)
                .build();

        userRepository.save(user);
        return user.getIdx();
    }
}

