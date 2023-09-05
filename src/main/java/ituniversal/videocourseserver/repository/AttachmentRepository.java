package ituniversal.videocourseserver.repository;

import ituniversal.videocourseserver.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@CrossOrigin
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    //    @Query("select a from attachment a where a.id=?1")
    Attachment findAttachmentById(UUID id);
}
