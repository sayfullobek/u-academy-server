package ituniversal.videocourseserver.controller;

import ituniversal.videocourseserver.entity.Module;
import ituniversal.videocourseserver.entity.Videos;
import ituniversal.videocourseserver.payload.ApiResponse;
import ituniversal.videocourseserver.repository.ModuleRepository;
import ituniversal.videocourseserver.repository.VideosRepository;
import ituniversal.videocourseserver.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/course-in/module")
@CrossOrigin
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;
    private final VideosRepository videosRepository;

    @PostMapping
    public HttpEntity<?> addModule(@RequestParam(name = "userId", required = false) UUID userId,
                                   @RequestParam(name = "courseId", required = false) UUID courseId,
                                   @RequestParam(name = "name", required = false) String name) {
        ApiResponse<?> apiResponse = moduleService.addModule(userId, courseId, name);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/lesson")
    public HttpEntity<?> addVideo(@RequestParam(name = "userId", required = false) UUID userId,
                                  @RequestParam(name = "courseId", required = false) UUID courseId,
                                  @RequestParam(name = "moduleId", required = false) UUID moduleId,
                                  @RequestParam(name = "videoName", required = false) String videoName,
                                  @RequestParam(name = "videoId", required = false) UUID videoId) {
        ApiResponse<?> apiResponse = moduleService.addVideo(userId, courseId, moduleId, videoName, videoId);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getVideosByModuleId(@PathVariable UUID id) {
        Module oneModule = moduleService.getOneModule(id);
        return ResponseEntity.ok(oneModule);
    }
}
