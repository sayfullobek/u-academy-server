package ituniversal.videocourseserver.entity;

import ituniversal.videocourseserver.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Videos extends AbsEntity {
    @Column(nullable = false, name = "video_name")
    private String videoName;

    @Column(nullable = false, name = "video")
    private UUID videoId;
}
