package com.cdtn.computerstore.dto.cart.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDetail {
    private Long productId;
    private String productName;
    private List<String> productImage;
    private Integer itemQuantity;
    private Long productLatestPrice;
    private Long totalPricePerProduct;
}
