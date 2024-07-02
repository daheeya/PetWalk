package Project.PetWalk.repository;

import Project.PetWalk.dto.OAuthProvider;
import Project.PetWalk.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmailAndServiceProvider(String email, OAuthProvider serviceProvider);
    Optional<UserEntity> findByEmailAndServiceProvider(String email, OAuthProvider serviceProvider);
}
