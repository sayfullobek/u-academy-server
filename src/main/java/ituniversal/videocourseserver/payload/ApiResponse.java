package ituniversal.videocourseserver.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import ituniversal.videocourseserver.entity.User;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ApiResponse<T> implements Serializable {
    private T body;
    private Integer status;
    private boolean success;
    private User user;

    public ApiResponse(T body, boolean success) {
        this.body = body;
        this.success = success;
    }

    public ApiResponse(T body, boolean success, Integer status) {
        this.body = body;
        this.success = success;
        this.status = status;
    }

    public ApiResponse(T body, Integer status, boolean success, User user) {
        this.body = body;
        this.status = status;
        this.success = success;
        this.user = user;
    }

    public ApiResponse(T body, @NonNull final Integer status, boolean success) {
        this.body = body;
        this.status = status;
        this.success = success;
    }
}
