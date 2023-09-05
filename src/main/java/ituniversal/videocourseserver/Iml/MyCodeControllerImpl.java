package ituniversal.videocourseserver.Iml;

import ituniversal.videocourseserver.payload.ApiResponse;
import ituniversal.videocourseserver.payload.ResMyCode;
import org.springframework.http.HttpEntity;

import java.util.List;
import java.util.UUID;

public interface MyCodeControllerImpl {
    HttpEntity<?> addMyCode(String name, UUID photoId, UUID codeId);

    HttpEntity<?> getCodeList();

    HttpEntity<?> getOneCode(UUID id);

    HttpEntity<?> deleteMyCode(UUID id);

    HttpEntity<?> editMyCode(UUID id, ResMyCode resMyCode);
}
