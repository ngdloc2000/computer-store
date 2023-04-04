package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.auth.request.AuthenticationRequest;
import com.cdtn.computerstore.dto.auth.request.RegistrationForm;
import com.cdtn.computerstore.dto.auth.response.AuthenticationResponse;
import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.service.AuthService;
import com.cdtn.computerstore.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponseData> login(@RequestBody @Valid AuthenticationRequest request) {

        AuthenticationResponse response = authService.authenticate(request);
        return ResponseEntity.ok(new BaseResponseData(200, "Success", response));
    }
}
