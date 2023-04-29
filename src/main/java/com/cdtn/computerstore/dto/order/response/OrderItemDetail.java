package com.cdtn.computerstore.dto.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDetail {
    private String productName;
    private Integer itemQuantity;
    private Long itemPrice;
}
