package ituniversal.videocourseserver.repository.rest;

import ituniversal.videocourseserver.entity.FAQ;
import ituniversal.videocourseserver.entity.Message;
import ituniversal.videocourseserver.projection.CustomFAQ;
import ituniversal.videocourseserver.projection.CustomMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "list", path = "message", excerptProjection = CustomMessage.class)
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
