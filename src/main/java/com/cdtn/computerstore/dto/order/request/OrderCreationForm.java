package com.cdtn.computerstore.dto.order.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderCreationForm {
    @NotNull
    private Long cartId;
    @NotNull
    private Long clientId;
    @NotBlank
    private String consigneeName;
    @NotBlank
    private String consigneePhoneNumber;
    @NotBlank
    private String deliveryAddress;
}
