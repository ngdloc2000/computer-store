package com.cdtn.computerstore.dto.cart.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDetail {
    private Long productId;
    private String productName;
    private String productImageMain;
    private Integer itemQuantity;
    private Long productLatestPrice;
    private Long totalPricePerProduct;
}
