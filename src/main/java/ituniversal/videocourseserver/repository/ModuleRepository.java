package ituniversal.videocourseserver.repository;

import ituniversal.videocourseserver.entity.Module;
import ituniversal.videocourseserver.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {
    @Query("select modules from courses where id=?1")
    List<Module> getModuleByCourseId(UUID courseId);
}
