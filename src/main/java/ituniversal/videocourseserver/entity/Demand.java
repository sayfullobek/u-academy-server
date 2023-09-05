package ituniversal.videocourseserver.entity;

import ituniversal.videocourseserver.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Demand extends AbsNameEntity { //kursning talablari
    @ManyToOne
    private Course course; //qaysi kursniki
}
