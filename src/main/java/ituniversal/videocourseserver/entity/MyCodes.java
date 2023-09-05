package ituniversal.videocourseserver.entity;

import ituniversal.videocourseserver.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MyCodes extends AbsEntity { //mening qilgan ishlarim file ko'rinishida
    @Column(nullable = false, unique = true)
    private String codeName;
    private UUID photoId;
    private UUID codeId;
}
