package ituniversal.videocourseserver.projection;

import ituniversal.videocourseserver.entity.FAQ;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "customFAQ", types = FAQ.class)
public interface CustomFAQ {
    Integer getId();

    String getName();

    String getFaqAbout();
}
