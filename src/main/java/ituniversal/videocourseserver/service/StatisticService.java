package ituniversal.videocourseserver.service;

import ituniversal.videocourseserver.payload.CourseDto;
import ituniversal.videocourseserver.payload.StatisticDto;
import ituniversal.videocourseserver.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final CourseRepository courseRepository;
    private final MyCodeRepository myCodeRepository;
    private final ModuleRepository moduleRepository;
    private final CommentRepository commentRepository;
    private final AuthRepository authRepository;

    public StatisticDto statisticDto() {
        return StatisticDto.builder()
                .courseSize(courseRepository.findAll().size())
                .myCodeSize(myCodeRepository.findAll().size())
                .moduleSize(moduleRepository.findAll().size())
                .commentSize(commentRepository.findAll().size())
                .authSize(authRepository.findAll().size() - 1)
                .build();
    }
}
