package ituniversal.videocourseserver.payload;

import ituniversal.videocourseserver.entity.Module;
import ituniversal.videocourseserver.entity.enums.CoursePriceName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResCourse {
    private UUID id;
    private String name;
    private String courseDescription;
    private String courseAbout; //kurs haqidagi ma'lumot
    private String updateAbout; //yangilanish haqidagi ma'lumot
    private Integer pupilSize; //ushbu kursda nechta o'quvchi o'qiydi
    private String about;
    private List<Module> modules;
    private Integer moduleSize; //modullar soni
    private Integer lessonsSize; //videolar soni
    private List<CommentDto> commentDtos; //sharhlar
    private ResTeacher resTeacher; //teacher ma'lumotlari
    private String teacherName;
    private String teacherTheCourseInfo; //ustozning ushbu kurs haqidagi iliq gaplari
    private List<WhatLearnDto> whatLearnDtos; //nimalarni o'rganishi ushbu kursdagi
    private List<DemandDto> demandDtos; //talablar haqida
    private UUID photoId;
    private CoursePriceName coursePriceName; //kurs tekin yoki pullimi
    private double umumiySoat; //kurs necha soat boladi umumiy
    private boolean isSertificate;
}
