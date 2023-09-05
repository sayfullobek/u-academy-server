package ituniversal.videocourseserver.utils;

import ituniversal.videocourseserver.entity.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PermitionsUrl {
    FAQ(AppConstant.API + "/FAQ/**", RoleName.ADMIN, "/FAQ"),
    COURSE(AppConstant.API + "/course/**", RoleName.ADMIN, "/course"),
    AUTH(AppConstant.API + "/auth/**", RoleName.ADMIN, null),
    CODE(AppConstant.API + "/my-code/**", RoleName.ADMIN, "/my-code"),
    SEND_MESSAGE(AppConstant.API + "/message/**", RoleName.ADMIN, "/message"),
    CONTACT(AppConstant.API + "/contact/**", RoleName.USER, "/contact"),
    MODULE(AppConstant.API + "/course-in/module/**", RoleName.USER, "/course-in/module");

    private final String url;
    private final RoleName allUrlOpen;
    private final String userOpenUrl;
}
