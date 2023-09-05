package ituniversal.videocourseserver.service;

import ituniversal.videocourseserver.Iml.CourseServiceImpl;
import ituniversal.videocourseserver.entity.Module;
import ituniversal.videocourseserver.entity.*;
import ituniversal.videocourseserver.entity.enums.CoursePriceName;
import ituniversal.videocourseserver.entity.enums.CourseStatus;
import ituniversal.videocourseserver.exception.ResourceNotFoundException;
import ituniversal.videocourseserver.payload.*;
import ituniversal.videocourseserver.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService implements CourseServiceImpl {
    private final AuthRepository authRepository;
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final VideosRepository videosRepository;
    private final CommentRepository commentRepository;
    private final WhatLearnRepository whatLearnRepository;
    private final DemandRepository demandRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public ApiResponse<?> saveCourse(CourseDto courseDto) {
        if (courseRepository.existsByNameEqualsIgnoreCase(courseDto.getName()))
            new ApiResponse<>("Bunday nomli kurs avvaldan mavjud", 409, false);
        User teacher = authRepository.findById(courseDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException(404, "getTeacher", "teacherId", courseDto.getUserId()));
        Course course = Course.builder()
                .name(courseDto.getName())
                .price(courseDto.getPrice())
                .sale(courseDto.getSale())
                .photoId(courseDto.getPhotoId())
                .user(teacher)
                .teacherTheCourseInfo(courseDto.getTeacherTheCourseInfo())
                .courseDescription(courseDto.getCourseDescription())
                .courseAbout(courseDto.getCourseAbout())
                .levelName(courseDto.getLevelName())
                .courseStatus(CourseStatus.NEW)
                .isSertivicate(true)
                .isActive(true)
                .build();
        courseRepository.save(course);
        return new ApiResponse<>("Muvaffaqiyatli saqlandi", 200, true);
    }

    @Override
    public ApiResponse<?> deleteCourse(UUID id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(404, "getCourse", "id", id));
        courseRepository.delete(course);
        return new ApiResponse<>("Muvaffaqiyatli o'chirildi", 200, true);
    }

    @Override
    public List<ResCourse> getCourse() {
        List<Course> all = courseRepository.findAll();
        List<ResCourse> resCourses = new ArrayList<>();
        for (Course course : all) {
            double sum = course.getPrice() - course.getSale();
            resCourses.add(
                    ResCourse.builder()
                            .id(course.getId())
                            .name(course.getName())
                            .coursePriceName(sum > 0 ? CoursePriceName.PAID : CoursePriceName.FREE)
                            .isSertificate(course.isSertivicate())
                            .courseDescription(course.getCourseDescription())
                            .photoId(course.getPhotoId())
                            .teacherName(course.getUser().getFirstName() + " " + course.getUser().getLastName())
                            .updateAbout(course.getUpdateAt().toString())
                            .build()
            );
        }
        return resCourses;
    }

    @Override
    public ResCourse getOneCourse(UUID id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(404, "getCourse", "id", id));
        int lessonSize = 0;
        for (Module module : course.getModules()) {
            lessonSize = lessonSize + module.getVideos().size();
        }
        return ResCourse.builder()
                .id(course.getId())
                .name(course.getName())
                .courseDescription(course.getCourseDescription())
                .updateAbout(course.getUpdateAt().toString())
                .pupilSize(course.getPupilSize())
                .about(course.getCourseAbout())
                .modules(course.getModules())
                .moduleSize(course.getModules().size())
                .lessonsSize(lessonSize)
                .commentDtos(getCommentsByCourse(commentRepository.findAllByCourseId(course.getId())))
                .resTeacher(getTeacherByCourse(course.getUser()))
                .teacherTheCourseInfo(course.getTeacherTheCourseInfo())
                .whatLearnDtos(getWhatLearnByCourse(course.getId()))
                .demandDtos(getDemandByCourse(course.getId()))
                .photoId(course.getPhotoId())
                .coursePriceName(course.getPrice() > course.getSale() ? CoursePriceName.PAID : CoursePriceName.FREE)
                .umumiySoat(getVideoHours(course.getModules()))
                .isSertificate(course.isSertivicate())
                .build();
    }

    public Long getVideoHours(List<Module> modules) {
        Long umumiySoat = 0L;
        for (Module module : modules) {
            for (Videos video : module.getVideos()) {
                Optional<Attachment> byId = attachmentRepository.findById(video.getVideoId());
                if (byId.isPresent()) {
                    Attachment attachment = byId.get();
                    umumiySoat += attachment.getSize();
                }
            }
        }
        return umumiySoat;
    }

    public List<DemandDto> getDemandByCourse(UUID id) {
        List<Demand> demands = demandRepository.findAllByCourseId(id);
        List<DemandDto> demandRess = new ArrayList<>();
        for (Demand whatLearn : demands) {
            demandRess.add(
                    DemandDto.builder()
                            .id(whatLearn.getId())
                            .name(whatLearn.getName())
                            .build()
            );
        }
        return demandRess;
    }

    public List<WhatLearnDto> getWhatLearnByCourse(UUID id) {
        List<WhatLearn> whatLearns = whatLearnRepository.findAllByCourseId(id);
        List<WhatLearnDto> whatLearnDtos = new ArrayList<>();
        for (WhatLearn whatLearn : whatLearns) {
            whatLearnDtos.add(
                    WhatLearnDto.builder()
                            .id(whatLearn.getId())
                            .name(whatLearn.getName())
                            .build()
            );
        }
        return whatLearnDtos;
    }

    public ResTeacher getTeacherByCourse(User teacher) {
        List<Course> teacherByCourses = courseRepository.findAllByUserId(teacher.getId());
        return ResTeacher.builder()
                .id(teacher.getId())
                .name(teacher.getFirstName())
                .surname(teacher.getLastName())
                .bio(teacher.getBio())
                .reyting("5")
                .pupilSize(String.valueOf(10))
                .courseSize(teacherByCourses.size())
                .teacherDescription(teacher.getAboutTeacher())
                .build();
    }

    public List<CommentDto> getCommentsByCourse(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentDtos.add(
                    CommentDto.builder()
                            .id(comment.getId())
                            .userId(comment.getUserId())
                            .user(authRepository.findById(comment.getUserId()).orElseThrow(() -> new ResourceNotFoundException(404, "getUser", "userId", comment.getUserId())))
                            .message(comment.getMessage())
                            .build()
            );
        }
        return commentDtos;
    }
}
