package ituniversal.videocourseserver.repository;

import ituniversal.videocourseserver.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
//    @Query("select a from comment a where a.course.id=?1")
    List<Comment> findAllByCourseId(UUID id);
}
