package ituniversal.videocourseserver.repository;

import ituniversal.videocourseserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin
public interface AuthRepository extends JpaRepository<User, UUID> {
    //    @Query("select u from users u where u.phoneNumber = ?1")
    Optional<User> findUserByPhoneNumber(String phoneNumber);

    //    @Query("select f from users f where f.phoneNumber = ?1")
    boolean existsByPhoneNumber(String phoneNumber);
}
