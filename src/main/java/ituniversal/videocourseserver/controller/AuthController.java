package ituniversal.videocourseserver.controller;

import ituniversal.videocourseserver.entity.User;
import ituniversal.videocourseserver.payload.*;
import ituniversal.videocourseserver.repository.AuthRepository;
import ituniversal.videocourseserver.security.JwtTokenProvider;
import ituniversal.videocourseserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final
    AuthService authService;
    private final
    AuthenticationManager authenticationManager;
    private final
    AuthRepository authRepository;
    private final
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword())
        );
        User user = authRepository.findUserByPhoneNumber(request.getPhoneNumber()).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        ResToken resToken = new ResToken(generateToken(request.getPhoneNumber()));
        System.out.println(ResponseEntity.ok(getMal(user, resToken)));
        return ResponseEntity.ok(getMal(user, resToken));
    }

    private String generateToken(String phoneNumber) {
        User user = authRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("getUser"));
        return jwtTokenProvider.generateToken(user.getId());
    }

    public GetData getMal(User user, ResToken resToken) {
        return new GetData(user, resToken);
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDto registerDto) {
        ApiResponse<?> register = authService.register(registerDto);
        return ResponseEntity.status(register.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(register);
    }

    @PutMapping("/change-password/{id}")
    public HttpEntity<?> changePassword(@PathVariable UUID id, @RequestBody RegisterDto registerDto) {
        ApiResponse<?> apiResponse = authService.changePassword(id, registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/buy-course/{id}")
    public HttpEntity<?> buyFreeCourse(@PathVariable UUID id, @RequestBody RegisterDto registerDto) {
        ApiResponse<?> apiResponse = authService.buyFreeCourse(id, registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
