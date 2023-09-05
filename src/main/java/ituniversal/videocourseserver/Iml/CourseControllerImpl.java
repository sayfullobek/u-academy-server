package ituniversal.videocourseserver.Iml;

import ituniversal.videocourseserver.payload.CourseDto;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface CourseControllerImpl {
    HttpEntity<?> saveCourse(CourseDto courseDto);

    HttpEntity<?> deleteCourse(UUID id);

    HttpEntity<?> getCourseList();

    HttpEntity<?> getOneCourse(UUID id);
}
