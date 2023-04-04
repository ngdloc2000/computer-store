package com.cdtn.computerstore.dto.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDetailResponse {

    private Long userId;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;
    private LocalDate dob;
}
