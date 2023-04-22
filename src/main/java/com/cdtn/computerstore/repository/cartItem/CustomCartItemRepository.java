package com.cdtn.computerstore.repository.cartItem;

import com.cdtn.computerstore.dto.cart.response.CartItemDetail;

import java.util.List;

public interface CustomCartItemRepository {

    List<CartItemDetail> getItemInCart(Long cartId, Long clientId);
}
