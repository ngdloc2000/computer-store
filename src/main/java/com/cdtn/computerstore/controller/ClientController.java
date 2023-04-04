package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.auth.request.RegistrationForm;
import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/register-account")
    public ResponseEntity<BaseResponseData> reigisterClient(@RequestBody @Valid RegistrationForm registrationForm) {

        return ResponseEntity.ok(clientService.registerClient(registrationForm));
    }
}
