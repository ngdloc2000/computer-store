package com.cdtn.computerstore.dto.order.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderCheckoutRequest {
    @NotNull
    private String clientId;
    @NotBlank
    private String currency;
    @NotBlank
    private String productName;
    @NotBlank
    private Long amount;
    @NotBlank
    private Long quantity;
    @NotBlank
    private String imageProduct;
}
