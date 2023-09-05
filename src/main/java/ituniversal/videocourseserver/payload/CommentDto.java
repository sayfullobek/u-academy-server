package ituniversal.videocourseserver.payload;

import ituniversal.videocourseserver.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private UUID id;
    private UUID userId;
    private User user;
    private String message;
}
