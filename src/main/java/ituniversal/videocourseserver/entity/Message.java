package ituniversal.videocourseserver.entity;

import ituniversal.videocourseserver.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message extends AbsNameEntity {
    private String surname;
    private String message;
}
