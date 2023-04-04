package com.cdtn.computerstore.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationForm {

    @Email
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String clientName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String address;

    @NotNull
    private LocalDate dob;
}
