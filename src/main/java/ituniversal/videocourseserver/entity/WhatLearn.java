package ituniversal.videocourseserver.entity;

import ituniversal.videocourseserver.entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WhatLearn extends AbsNameEntity {
    @ManyToOne
    private Course course; //qaysi kursniki
}
