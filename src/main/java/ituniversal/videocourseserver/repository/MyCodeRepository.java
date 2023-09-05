package ituniversal.videocourseserver.repository;

import ituniversal.videocourseserver.entity.MyCodes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MyCodeRepository extends JpaRepository<MyCodes, UUID> {
    boolean existsByCodeNameEqualsIgnoreCase(String codeName);
}
