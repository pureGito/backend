package puregito.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import puregito.backend.entity.ActivateCode;
import puregito.backend.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ActivateCodeRepository extends JpaRepository<ActivateCode, Long> {


    Optional<ActivateCode> findByCode(String code);

    Optional<ActivateCode> findByUser_Id(Long id);

    boolean existsByCode(String code);









}