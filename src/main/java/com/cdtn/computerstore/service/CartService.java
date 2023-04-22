package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.cart.mapper.CartMapper;
import com.cdtn.computerstore.dto.cart.response.CartDetail;
import com.cdtn.computerstore.dto.cart.response.CartItemDetail;
import com.cdtn.computerstore.entity.Cart;
import com.cdtn.computerstore.entity.CartItem;
import com.cdtn.computerstore.entity.Product;
import com.cdtn.computerstore.enums.ProductEnum;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.cart.CartRepository;
import com.cdtn.computerstore.repository.cartItem.CartItemRepository;
import com.cdtn.computerstore.repository.cartItem.CustomCartItemRepositoryImpl;
import com.cdtn.computerstore.repository.client.ClientRepository;
import com.cdtn.computerstore.repository.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final CustomCartItemRepositoryImpl customCartItemRepository;

    private final CartMapper cartMapper;

    @Transactional
    public void getCartIdAfterCreateCart(Long clientId, Long productId) {

        if (!clientRepository.existsById(clientId)) {
            throw new StoreException("Client is not exists");
        }

        Product product = checkProductValid(productId);
        Optional<Cart> activeCart = cartRepository.findByClientIdAndActiveCart(clientId);

        if (activeCart.isEmpty()) {
            Cart newCart = cartRepository.save(cartMapper.createCart(clientId));

            cartItemRepository.save(cartMapper.createCartItem(newCart.getId(), product));
        } else {
            CartItem cartItem = cartItemRepository.findByCartIdAndProductId(activeCart.get().getId(), productId);
            cartMapper.updateCart(activeCart.get());
            cartRepository.save(activeCart.get());

            if (Objects.isNull(cartItem)) {
                cartItemRepository.save(cartMapper.createCartItem(activeCart.get().getId(), product));
            } else {
                cartMapper.updateCartItemWhenAddProduct(cartItem, product);
                cartItemRepository.save(cartItem);
            }
        }
    }

    @Transactional
    public void updateProductInCartWhenRemoveProduct(Long cartId, Long productId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new StoreException("Cart not found by id: " + cartId));
        CartItem cartItem = cartItemRepository.findByCartIdAndProductIdAndStatusActive(cartId, productId)
                .orElseThrow(() -> new StoreException("CartItem is not exists"));

        cartMapper.updateCart(cart);
        cartMapper.updateCartItemWhenRemoveProduct(cartItem);

        cartRepository.save(cart);
        cartItemRepository.save(cartItem);
    }

    public CartDetail getCartDetailByUserId(Long userId) {

        Optional<Cart> cart = cartRepository.findByClientIdAndActiveCart(userId);

        if (cart.isEmpty()) {
            return null;
        }

        List<CartItemDetail> cartItemDetail = customCartItemRepository.getItemInCart(cart.get().getId(), userId);
        CartDetail cartDetail = cartMapper.createCartDetail(cart.get().getId(), cartItemDetail);

        return cartDetail;
    }

    public Product checkProductValid(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new StoreException("Product not found with id: " + productId));

        if (!product.getStatus().equals(ProductEnum.Status.ACTIVE.getValue())) {
            throw new StoreException("Product status is not active");
        }

        return product;
    }
}
