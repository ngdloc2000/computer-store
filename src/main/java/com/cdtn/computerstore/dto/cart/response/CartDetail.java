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
public class CartDetail {

    private Long cartId;
    private Long cartPrice;
    private List<CartItemDetail> cartItemDetailList;
}
