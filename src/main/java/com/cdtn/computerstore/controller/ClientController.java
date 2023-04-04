package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.auth.request.RegistrationForm;
import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/registerAccount")
    public ResponseEntity<BaseResponseData> registerClient(@RequestBody @Valid RegistrationForm registrationForm) {

        return ResponseEntity.ok(clientService.registerClient(registrationForm));
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponseData> getInfoClient(@RequestParam @NotNull Long userId) {

        return ResponseEntity.ok(clientService.findByUserId(userId));
    }
}
