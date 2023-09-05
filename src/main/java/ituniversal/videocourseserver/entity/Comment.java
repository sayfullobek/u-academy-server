package ituniversal.videocourseserver.entity;

import ituniversal.videocourseserver.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment extends AbsEntity {
    @ManyToOne
    private Course course; //qaysi kursga komment yozilyapti

    private UUID userId;

    @Column(nullable = false, length = 99999)
    private String message;
}
