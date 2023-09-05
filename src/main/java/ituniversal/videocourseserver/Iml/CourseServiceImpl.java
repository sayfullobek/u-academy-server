package ituniversal.videocourseserver.Iml;

import ituniversal.videocourseserver.payload.ApiResponse;
import ituniversal.videocourseserver.payload.CourseDto;
import ituniversal.videocourseserver.payload.ResCourse;

import java.util.List;
import java.util.UUID;

public interface CourseServiceImpl {
    ApiResponse<?> saveCourse(CourseDto courseDto);

    ApiResponse<?> deleteCourse(UUID id);

    List<ResCourse> getCourse();

    ResCourse getOneCourse(UUID id);
}
