package Project.PetWalk.service;

import Project.PetWalk.dto.*;
import Project.PetWalk.entity.PostEntity;
import Project.PetWalk.entity.UserEntity;
import Project.PetWalk.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    @Transactional(readOnly = true)
    public boolean isUserExists(String email, OAuthProvider oauthProvider) {
        return userRepository.existsByEmailAndServiceProvider(email, oauthProvider);
    }
    @Transactional(readOnly = true)
    public Optional<UserEntity> findByEmailAndServiceProvider(String email, OAuthProvider serviceProvider) {
        return userRepository.findByEmailAndServiceProvider(email, serviceProvider);
    }

    @Transactional
    public UserEntity saveNaverUserInfo(NaverUserInfo naverUserInfo) {
        String nickname = naverUserInfo.getResponse().getName();
        String email = naverUserInfo.getResponse().getEmail();
        String phon_number = naverUserInfo.getResponse().getMobile();

        if (isUserExists(email, OAuthProvider.NAVER)) {
            log.warn("이미 등록된 사용자입니다: {}", email);
            return null; // 이미 등록된 사용자일 경우 저장하지 않고 null을 반환하거나 다른 처리를 수행할 수 있습니다.
        }

        UserEntity userEntity = UserEntity.builder()
                .nickname(nickname)
                .email(email)
                .phoneNumber(phon_number)
                .serviceProvider(OAuthProvider.NAVER)
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
                .serviceProvider(OAuthProvider.KAKAO)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return userRepository.save(userEntity);
    }
//    public UserDto getUserById(Long id) {
//        Optional<UserEntity> userEntity = userRepository.findById(id);
//        return userEntity.map(entity -> UserDto.builder()
//                .idx(entity.getIdx())
//                .username(entity.getNickname())
//                .email(entity.getEmail())
//                .phoneNumber(entity.getPhoneNumber())
//                .address(entity.getAddress())
//                .point(entity.getPoint())
//                .build()).orElse(null);
//    }
}


