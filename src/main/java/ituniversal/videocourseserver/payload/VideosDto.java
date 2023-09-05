package ituniversal.videocourseserver.payload;

import lombok.Getter;

import java.util.UUID;

@Getter
public class VideosDto {
    private UUID id;
    private String videoName;
    private UUID videoId;
}
