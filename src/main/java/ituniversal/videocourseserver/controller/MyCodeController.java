package ituniversal.videocourseserver.controller;

import ituniversal.videocourseserver.Iml.MyCodeControllerImpl;
import ituniversal.videocourseserver.payload.ApiResponse;
import ituniversal.videocourseserver.payload.ResMyCode;
import ituniversal.videocourseserver.service.MyCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/my-code")
@CrossOrigin
@RequiredArgsConstructor
public class MyCodeController implements MyCodeControllerImpl {
    private final MyCodeService myCodeService;

    @PostMapping
    @Override
    public HttpEntity<?> addMyCode(@RequestParam(name = "name", required = false) String name,
                                   @RequestParam(name = "photoId", required = false) UUID photoId,
                                   @RequestParam(name = "codeId", required = false) UUID codeId) {
        ApiResponse<?> apiResponse = myCodeService.addMyCode(name, photoId, codeId);
        return ResponseEntity.status(apiResponse.isSuccess() ? apiResponse.getStatus() : 409).body(apiResponse);
    }

    @GetMapping
    @Override
    public HttpEntity<?> getCodeList() {
        List<ResMyCode> codeList = myCodeService.getCodeList();
        return ResponseEntity.ok(codeList);
    }

    @GetMapping("/{id}")
    @Override
    public HttpEntity<?> getOneCode(@PathVariable UUID id) {
        ResMyCode oneCode = myCodeService.getOneCode(id);
        return ResponseEntity.ok(oneCode);
    }

    @DeleteMapping("/{id}")
    @Override
    public HttpEntity<?> deleteMyCode(@PathVariable UUID id) {
        ApiResponse<?> apiResponse = myCodeService.deleteMyCode(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? apiResponse.getStatus() : 409).body(apiResponse);
    }

    @Override
    public HttpEntity<?> editMyCode(UUID id, ResMyCode resMyCode) {
        return null;
    }
}
