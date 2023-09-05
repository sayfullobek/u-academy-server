package ituniversal.videocourseserver.entity;

import ituniversal.videocourseserver.entity.enums.CourseStatus;
import ituniversal.videocourseserver.entity.enums.LevelName;
import ituniversal.videocourseserver.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "courses")
public class Course extends AbsEntity {
    @Column(name = "course_name", nullable = false, unique = true)
    private String name; //kurs nomi

    @Column(name = "course_price", nullable = false)
    private double price; //narxi

    @Column(name = "course_sale")
    private double sale; //chegirma

    @Column(name = "course_photo")
    private UUID photoId; //kurs rasmi

    @ManyToOne(optional = false)
//    @JoinColumn(name = "teacher")
    private User user; //kim tomonidan ishlab chiqildi

    @Column(nullable = false)
    private String teacherTheCourseInfo; //ushbu kursning ustozi matvivatsion gaplari

    @OneToMany
    private List<Module> modules; //modullari

    @Column(name = "course_description", nullable = false, length = 9999)
    private String courseDescription; //kurs haqidagi reklama gaplar

    @Column(name = "course_about", nullable = false, length = 9999)
    private String courseAbout; //kurs haqidagi ma'lumot

    @Enumerated(value = EnumType.STRING)
    private LevelName levelName; //kursning darajasi

    @Enumerated(value = EnumType.STRING)
    private CourseStatus courseStatus; //kurs yangi yoki eskiligi haqida

    private Integer pupilSize;

    private boolean isSertivicate;
    private boolean isActive;
}
