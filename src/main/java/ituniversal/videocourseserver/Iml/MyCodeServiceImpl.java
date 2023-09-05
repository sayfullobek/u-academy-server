package ituniversal.videocourseserver.Iml;

import ituniversal.videocourseserver.payload.ApiResponse;
import ituniversal.videocourseserver.payload.ResMyCode;

import java.util.List;
import java.util.UUID;

public interface MyCodeServiceImpl {
    ApiResponse<?> addMyCode(String name, UUID photoId, UUID codeId);

    List<ResMyCode> getCodeList();

    ResMyCode getOneCode(UUID id);

    ApiResponse<?> deleteMyCode(UUID id);

    ApiResponse<?> editMyCode(UUID id, ResMyCode resMyCode);
}
