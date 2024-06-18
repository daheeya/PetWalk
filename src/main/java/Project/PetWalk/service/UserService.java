package Project.PetWalk.service;

import Project.PetWalk.dto.KakaoUserInfo;
import Project.PetWalk.dto.NaverUserInfo;
import Project.PetWalk.entity.UserEntity;
import Project.PetWalk.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public UserEntity saveNaverUserInfo(NaverUserInfo naverUserInfo) {
        String nickname = naverUserInfo.getResponse().getNickname();
        String email = naverUserInfo.getResponse().getEmail();
        String phon_number = naverUserInfo.getResponse().getMobile();

        UserEntity userEntity = UserEntity.builder()
                .nickname(nickname)
                .email(email)
                .phoneNumber(phon_number)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return userRepository.save(userEntity);
    }

    @Transactional
    public UserEntity saveKakaoUserInfo(KakaoUserInfo kakaoUserInfo) {
        String nickname = kakaoUserInfo.getKakaoAccount().getProfile().getNickname();
        String email = kakaoUserInfo.getKakaoAccount().getEmail();

        log.info("Kakao 사용자 정보 저장: nickname={}, email={}", nickname, email);

        UserEntity userEntity = UserEntity.builder()
                .nickname(nickname)
                .email(email)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return userRepository.save(userEntity);
    }
}


