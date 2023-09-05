package ituniversal.videocourseserver.entity;

import ituniversal.videocourseserver.entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class FAQ extends AbsNameEntity {
    @Column(length = 9999)
    private String faqAbout;
}
