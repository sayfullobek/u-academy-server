package ituniversal.videocourseserver.entity;

import ituniversal.videocourseserver.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Module extends AbsEntity {
    @Column(nullable = false, name = "module_name", unique = true)
    private String moduleName;

    @OneToMany
    private List<Videos> videos; //videolari
}
