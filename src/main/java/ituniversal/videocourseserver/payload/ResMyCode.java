package ituniversal.videocourseserver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResMyCode {
    private UUID id;
    private String codeName;
    private UUID photoId;
    private UUID codeId;
    private String codeType;
    private double size;
    private String whenUpload;
}
