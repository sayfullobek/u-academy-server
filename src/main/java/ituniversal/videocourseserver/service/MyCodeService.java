package ituniversal.videocourseserver.service;

import ituniversal.videocourseserver.Iml.MyCodeServiceImpl;
import ituniversal.videocourseserver.entity.Attachment;
import ituniversal.videocourseserver.entity.AttachmentContent;
import ituniversal.videocourseserver.entity.MyCodes;
import ituniversal.videocourseserver.exception.ResourceNotFoundException;
import ituniversal.videocourseserver.payload.ApiResponse;
import ituniversal.videocourseserver.payload.ResMyCode;
import ituniversal.videocourseserver.repository.AttachmentContentRepository;
import ituniversal.videocourseserver.repository.AttachmentRepository;
import ituniversal.videocourseserver.repository.MyCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MyCodeService implements MyCodeServiceImpl {
    private final MyCodeRepository myCodeRepository;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @Override
    public ApiResponse<?> addMyCode(String name, UUID photoId, UUID codeId) {
        if (myCodeRepository.existsByCodeNameEqualsIgnoreCase(name))
            return new ApiResponse<>("Bunday fayl nomi avvaldan mavjud", 409, false);
        MyCodes myCodes = MyCodes.builder()
                .codeName(name)
                .codeId(codeId)
                .photoId(photoId)
                .build();
        myCodeRepository.save(myCodes);
        return new ApiResponse<>("Muvaffaqiyatli saqlandi", 200, true);
    }

    @Override
    public List<ResMyCode> getCodeList() {
        List<MyCodes> all = myCodeRepository.findAll();
        List<ResMyCode> resMyCodes = new ArrayList<>();
        for (MyCodes myCodes : all) {
            Attachment attachment = attachmentRepository.findById(myCodes.getCodeId()).orElseThrow(() -> new ResourceNotFoundException(404, "getAttachment", "getCodeId", myCodes.getCodeId()));
            resMyCodes.add(ResMyCode.builder()
                    .id(myCodes.getId())
                    .codeName(myCodes.getCodeName())
                    .codeId(myCodes.getCodeId())
                    .photoId(myCodes.getPhotoId())
                    .codeType(attachment.getContentType())
                    .size(attachment.getSize())
                    .whenUpload(myCodes.getCreateAt().toString())
                    .build()
            );
        }
        return resMyCodes;
    }

    @Override
    public ResMyCode getOneCode(UUID id) {
        MyCodes myCodes = myCodeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(404, "getMyCodes", "id", id));
        Attachment attachment = attachmentRepository.findById(myCodes.getCodeId()).orElseThrow(() -> new ResourceNotFoundException(404, "getAttachment", "getCodeId", myCodes.getCodeId()));
        return ResMyCode.builder()
                .id(myCodes.getId())
                .codeName(myCodes.getCodeName())
                .codeId(myCodes.getCodeId())
                .photoId(myCodes.getPhotoId())
                .codeType(attachment.getContentType())
                .size(attachment.getSize())
                .whenUpload(myCodes.getCreateAt().toString())
                .build();
    }

    @Override
    public ApiResponse<?> deleteMyCode(UUID id) {
        MyCodes myCodes = myCodeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(404, "getMyCodes", "id", id));
        AttachmentContent photoContent = attachmentContentRepository.findByAttachmentId(myCodes.getPhotoId());
        AttachmentContent codeContent = attachmentContentRepository.findByAttachmentId(myCodes.getCodeId());
        Attachment attachment = attachmentRepository.findById(myCodes.getPhotoId()).orElseThrow(() -> new ResourceNotFoundException(404, "getAttachment", "getPhotoId", myCodes.getPhotoId()));
        Attachment attachment1 = attachmentRepository.findById(myCodes.getCodeId()).orElseThrow(() -> new ResourceNotFoundException(404, "getAttachment", "getCodeId", myCodes.getCodeId()));
        attachmentContentRepository.delete(photoContent);
        attachmentContentRepository.delete(codeContent);
        attachmentRepository.delete(attachment);
        attachmentRepository.delete(attachment1);
        myCodeRepository.delete(myCodes);
        return new ApiResponse<>("Muvaffaqiyatli o'chirildi", 200, true);
    }

    @Override
    public ApiResponse<?> editMyCode(UUID id, ResMyCode resMyCode) {
        return null;
    }
}
