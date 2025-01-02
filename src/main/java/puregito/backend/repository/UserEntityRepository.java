package puregito.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puregito.backend.entity.UserEntity;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    UserEntity findByPhone(String phone);

    UserEntity findUserEntitiesById(Long id);


}