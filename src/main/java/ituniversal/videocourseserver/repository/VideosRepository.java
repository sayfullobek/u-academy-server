package ituniversal.videocourseserver.repository;

import ituniversal.videocourseserver.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VideosRepository extends JpaRepository<Videos, UUID> {
    List<Videos> findModuleById(UUID moduleId);
}
