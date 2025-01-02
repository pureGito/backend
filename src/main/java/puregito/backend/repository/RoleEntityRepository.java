package puregito.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puregito.backend.entity.RoleEntity;
import puregito.backend.entity.Roles;

import java.util.Optional;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(Roles name);



}