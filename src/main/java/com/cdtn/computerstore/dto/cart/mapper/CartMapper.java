package com.cdtn.computerstore.dto.cart.mapper;

import com.cdtn.computerstore.dto.cart.response.CartDetail;
import com.cdtn.computerstore.dto.cart.response.CartItemDetail;
import com.cdtn.computerstore.entity.Cart;
import com.cdtn.computerstore.entity.CartItem;
import com.cdtn.computerstore.entity.Product;
import com.cdtn.computerstore.enums.CartEnum;
import com.cdtn.computerstore.enums.CartItemEnum;
import com.cdtn.computerstore.exception.StoreException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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

        this.checkProductQuantity(product.getQuantity(), 1);

        return CartItem.builder()
                .cartId(cartId)
                .productId(product.getId())
                .quantity(1)
                .status(CartItemEnum.Status.ACTIVE.getValue())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void updateCartItemWhenAddProduct(CartItem cartItem, Product product) {

        if (CartItemEnum.Status.INACTIVE.getValue().equals(cartItem.getStatus())) {
            cartItem.setQuantity(1);
            cartItem.setStatus(CartItemEnum.Status.ACTIVE.getValue());
        } else if (CartItemEnum.Status.ACTIVE.getValue().equals(cartItem.getStatus())) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        this.checkProductQuantity(product.getQuantity(), cartItem.getQuantity());
        cartItem.setUpdatedAt(LocalDateTime.now());
    }

    public void updateCartItemWhenRemoveProduct(CartItem cartItem, Integer deleteAll) {
        if (cartItem.getQuantity() == 1 || deleteAll == 1) {
            cartItem.setQuantity(0);
            cartItem.setStatus(CartItemEnum.Status.INACTIVE.getValue());
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        }
        cartItem.setUpdatedAt(LocalDateTime.now());
    }

    public CartDetail createCartDetail(Long cartId, List<CartItemDetail> cartItemDetailList) {

        Long cartPrice = cartItemDetailList.stream()
                .mapToLong(CartItemDetail::getTotalPricePerProduct)
                .sum();

        return CartDetail.builder()
                .cartId(cartId)
                .cartPrice(cartPrice)
                .cartItemDetailList(cartItemDetailList)
                .build();
    }

    private void checkProductQuantity(Integer oldQuantity, Integer newQuantity) {

        if (newQuantity > oldQuantity) {
            throw new StoreException("Số lượng đặt hàng vượt quá số lượng tồn kho");
        }
    }
}
