package ituniversal.videocourseserver.component;

import ituniversal.videocourseserver.entity.Role;
import ituniversal.videocourseserver.entity.User;
import ituniversal.videocourseserver.entity.enums.RoleName;
import ituniversal.videocourseserver.repository.AuthRepository;
import ituniversal.videocourseserver.repository.CourseRepository;
import ituniversal.videocourseserver.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;

    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        if (init.equals("create-drop") || init.equals("create")) {
            for (RoleName value : RoleName.values()) {
                roleRepository.save(new Role(value));
            }
            User teacher = authRepository.save(
                    new User(
                            "Sayfullo", "To'xtayev", "990763246", passwordEncoder.encode("root123"), Collections.singleton(roleRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("getRole"))), true, true, true, true
                    )
            );
//            courseRepository.save(
//                    Course.builder()
//                            .name("qozi")
//                            .price(10)
//                            .sale(10)
//                            .user(teacher)
//                            .teacherTheCourseInfo("a")
//                            .courseDescription("a")
//                            .courseAbout("a")
//                            .levelName(LevelName.EASY)
//                            .courseStatus(CourseStatus.NEW)
//                            .pupilSize(10)
//                            .isActive(true)
//                            .isSertivicate(true)
//                            .build()
//            );
        }
    }
}
