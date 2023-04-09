package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.auth.request.AuthenticationRequest;
import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponseData> login(@RequestBody @Valid AuthenticationRequest request) {

        return ResponseEntity.ok(authService.authenticate(request));
    }
}
