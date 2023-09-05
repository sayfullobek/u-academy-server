package ituniversal.videocourseserver.repository;

import ituniversal.videocourseserver.entity.Course;
import ituniversal.videocourseserver.entity.Module;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    //    @Query("select cor from courses cor where cor.name = ?1")
    boolean existsByNameEqualsIgnoreCase(String name);

    Course findByName(String name);

    //    @Query("select cor from courses cor where cor.user.id=?1")
    List<Course> findAllByUserId(UUID user_id);
}
