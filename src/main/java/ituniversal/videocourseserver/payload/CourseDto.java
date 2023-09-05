package ituniversal.videocourseserver.payload;

import ituniversal.videocourseserver.entity.enums.CourseStatus;
import ituniversal.videocourseserver.entity.enums.LevelName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    private String name;
    private double price;
    private double sale;
    private UUID photoId;
    private UUID userId;
    private String teacherTheCourseInfo;
    private UUID moduleId;
    private String courseDescription;
    private String courseAbout;
    private LevelName levelName;
    private CourseStatus courseStatus;
    private final boolean isSertivicate = true;
    private final boolean isActive = true;
}
