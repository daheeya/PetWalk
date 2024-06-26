package Project.PetWalk.repository;

import Project.PetWalk.dto.OAuthProvider;
import Project.PetWalk.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmailAndServiceProvider(String email, OAuthProvider serviceProvider);
}
