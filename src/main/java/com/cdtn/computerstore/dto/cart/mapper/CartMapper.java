package com.cdtn.computerstore.dto.cart.mapper;

import com.cdtn.computerstore.entity.Cart;
import com.cdtn.computerstore.entity.CartItem;
import com.cdtn.computerstore.entity.Product;
import com.cdtn.computerstore.enums.CartEnum;
import com.cdtn.computerstore.enums.CartItemEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CartMapper {

    public Cart createCart(Long clientId) {

        return Cart.builder()
                .clientId(clientId)
                .status(CartEnum.Status.ACTIVE.getValue())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public CartItem createCartItem(Long cartId, Product product) {

        return CartItem.builder()
                .cartId(cartId)
                .productId(product.getId())
                .quantity(1L)
                .price(product.getLatestPrice())
                .status(CartItemEnum.Status.ACTIVE.getValue())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void updateCart(Cart cart) {

        cart.setUpdatedAt(LocalDateTime.now());
    }

    public void updateCartItemWhenAddProduct(CartItem cartItem, Product product) {

        if (CartItemEnum.Status.INACTIVE.getValue().equals(cartItem.getStatus())) {
            cartItem.setQuantity(1L);
            cartItem.setPrice(product.getLatestPrice());
            cartItem.setStatus(CartItemEnum.Status.ACTIVE.getValue());
        } else if (CartItemEnum.Status.ACTIVE.getValue().equals(cartItem.getStatus())){
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        cartItem.setUpdatedAt(LocalDateTime.now());
    }

    public void updateCartItemWhenRemoveProduct(CartItem cartItem) {

        if (cartItem.getQuantity() == 1) {
            cartItem.setQuantity(0L);
            cartItem.setStatus(CartItemEnum.Status.INACTIVE.getValue());
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        }
        cartItem.setUpdatedAt(LocalDateTime.now());
    }
}
