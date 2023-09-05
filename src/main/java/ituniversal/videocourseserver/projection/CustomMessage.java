package ituniversal.videocourseserver.projection;

import ituniversal.videocourseserver.entity.FAQ;
import ituniversal.videocourseserver.entity.Message;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "customMessage", types = Message.class)
public interface CustomMessage {
    Integer getId();

    String getName();

    String getSurname();

    String getMessage();
}
