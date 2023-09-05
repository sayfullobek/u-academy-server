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
public class ResTeacher {
    private UUID id;
    private String name;
    private String surname;
    private String bio;
    private String reyting;
    private String pupilSize; //ushbu o'qituvchi nechta o'quvchi o'qitgan
    private Integer courseSize; //nechta kurs joylagan appga
    private String teacherDescription;
}
