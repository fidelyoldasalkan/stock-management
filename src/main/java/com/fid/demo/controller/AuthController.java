package com.fid.demo.controller;

import com.fid.demo.auth.TokenManager;
import com.fid.demo.controller.request.AddUserRequest;
import com.fid.demo.controller.request.LoginRequest;
import com.fid.demo.controller.response.GeneralResponse;
import com.fid.demo.service.IUserService;
import com.fid.demo.util.GeneralResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;
    private final IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return ResponseEntity.ok(GeneralResponseBuilder.success(tokenManager.generateToken(request.getUsername())));
    }

    @PostMapping("/signUp")
    public ResponseEntity<GeneralResponse> signUp(@RequestBody AddUserRequest request) {
        userService.signUp(request);
        return ResponseEntity.ok(GeneralResponseBuilder.success(null));
    }

    @GetMapping("/token")
    public ResponseEntity<GeneralResponse> token() {
        tokenManager.test();
        return ResponseEntity.ok(GeneralResponseBuilder.success(null));
    }

    @GetMapping("/test")
    public ResponseEntity<GeneralResponse> test() {
        return ResponseEntity.ok(GeneralResponseBuilder.success(null));
    }
}
