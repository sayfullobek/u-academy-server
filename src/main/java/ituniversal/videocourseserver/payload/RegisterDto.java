package ituniversal.videocourseserver.payload;

import lombok.*;
import org.springframework.hateoas.NonComposite;

import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDto {
    private UUID id;
    private UUID courseId;
    private String firstName;
    private String lastName;

    @Pattern(regexp = "^+?([9]{2}[8])?[0-9]{9}$", message = "Telefon raqamda xatolik", groups = Error.class)
    private String phoneNumber;

    private String password;
    private String newPassword;
    private Integer roleId;
    private boolean enabled;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
}
