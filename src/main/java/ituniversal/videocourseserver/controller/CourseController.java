package ituniversal.videocourseserver.controller;

import ituniversal.videocourseserver.Iml.CourseControllerImpl;
import ituniversal.videocourseserver.payload.ApiResponse;
import ituniversal.videocourseserver.payload.CourseDto;
import ituniversal.videocourseserver.payload.ResCourse;
import ituniversal.videocourseserver.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController implements CourseControllerImpl {
    private final CourseService courseService;

    @PostMapping
    @Override
    public HttpEntity<?> saveCourse(@RequestBody CourseDto courseDto) {
        ApiResponse<?> apiResponse = courseService.saveCourse(courseDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    @Override
    public HttpEntity<?> deleteCourse(@PathVariable UUID id) {
        ApiResponse<?> apiResponse = courseService.deleteCourse(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    @Override
    @Lazy
    public HttpEntity<?> getCourseList() {
        List<ResCourse> course = courseService.getCourse();
        return ResponseEntity.ok(course);
    }

    @GetMapping("/{id}")
    @Override
    public HttpEntity<?> getOneCourse(@PathVariable UUID id) {
        ResCourse oneCourse = courseService.getOneCourse(id);
        return ResponseEntity.ok(oneCourse);
    }
}
