package ituniversal.videocourseserver.service;

import ituniversal.videocourseserver.entity.Course;
import ituniversal.videocourseserver.entity.Module;
import ituniversal.videocourseserver.entity.User;
import ituniversal.videocourseserver.entity.Videos;
import ituniversal.videocourseserver.exception.ResourceNotFoundException;
import ituniversal.videocourseserver.payload.ApiResponse;
import ituniversal.videocourseserver.repository.AuthRepository;
import ituniversal.videocourseserver.repository.CourseRepository;
import ituniversal.videocourseserver.repository.ModuleRepository;
import ituniversal.videocourseserver.repository.VideosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final AuthRepository authRepository;
    private final VideosRepository videosRepository;

    public ApiResponse<?> addModule(UUID userId, UUID courseId, String name) {
        User user = authRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(404, "getUser", "userId", userId));
        if (user == null) {
            return new ApiResponse<>("Sizga module qo'shish mumkin emas", false, 404);
        }
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(404, "getCourse", "courseId", courseId));
        if (course == null) {
            return new ApiResponse<>("Bunday kurs mavjud emas", false, 404);
        }
        Module module = moduleRepository.save(
                Module.builder()
                        .moduleName(name)
                        .build()
        );
        course.getModules().add(module);
        courseRepository.save(course);
        return new ApiResponse<>("Muvaffaqiyatli saqlandi", true, 200);
    }

    public ApiResponse<?> addVideo(UUID userId, UUID courseId, UUID moduleId, String videoName, UUID videoId) {
        User user = authRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(404, "getUser", "userId", userId));
        if (user == null) {
            return new ApiResponse<>("Sizga video qo'shish mumkin emas", false, 404);
        }
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(404, "getCourse", "courseId", courseId));
        if (course == null) {
            return new ApiResponse<>("Bunday kurs mavjud emas", false, 404);
        }
        List<Module> modules = course.getModules();
        for (Module module : modules) {
            if (module.getId().equals(moduleId)) {
                Videos save = videosRepository.save(new Videos(videoName, videoId));
                module.getVideos().add(save);
                moduleRepository.save(module);
                courseRepository.save(course);
                return new ApiResponse<>("Muvaffaqiyatli video saqlandi", true, 200);
            }
        }
        return new ApiResponse<>("Bunday module mavjud emas", false, 404);
    }

    public Module getOneModule(UUID id) {
        return moduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(404, "getModule", "id", id));
    }
}
