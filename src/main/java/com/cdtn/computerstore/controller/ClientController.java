package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.auth.request.RegistrationForm;
import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.dto.client.response.ClientDetailResponse;
import com.cdtn.computerstore.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClientController {

    private final ClientService clientService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @PostMapping("/registerAccount")
    public ResponseEntity<BaseResponseData> registerClient(@RequestBody @Valid RegistrationForm registrationForm) {

        try {
            clientService.registerClient(registrationForm);
            return ResponseEntity.ok(new BaseResponseData(200, "Success", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, e.getMessage(), null));
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponseData> getInfoClient(@RequestParam @NotNull Long userId) {

        try {
            ClientDetailResponse response = clientService.findByUserId(userId);
            return ResponseEntity.ok(new BaseResponseData(200, "Success", response));
        } catch (Exception e) {
            LOGGER.error("Exception when /client/detail ", e);
            return ResponseEntity.ok(new BaseResponseData(500, e.getMessage(), null));
        }
    }
}
