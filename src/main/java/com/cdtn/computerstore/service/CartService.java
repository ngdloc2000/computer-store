package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.cart.mapper.CartMapper;
import com.cdtn.computerstore.dto.cart.response.CartDetail;
import com.cdtn.computerstore.dto.cart.response.CartItemDetail;
import com.cdtn.computerstore.entity.Cart;
import com.cdtn.computerstore.entity.CartItem;
import com.cdtn.computerstore.entity.Product;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.cart.CartRepository;
import com.cdtn.computerstore.repository.cartItem.CartItemRepository;
import com.cdtn.computerstore.repository.cartItem.CustomCartItemRepositoryImpl;
import com.cdtn.computerstore.repository.client.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ClientRepository clientRepository;
    private final CustomCartItemRepositoryImpl customCartItemRepository;

    private final ProductService productService;

    private final CartMapper cartMapper;

    @Transactional
    public void createCartWhenPickProduct(Long clientId, Long productId) {

        if (!clientRepository.findByUserId(clientId).isPresent()) {
            throw new StoreException("Client is not exists");
        }

        Product product = productService.checkProductValid(productId);
        Optional<Cart> activeCart = cartRepository.findByClientIdAndActiveCart(clientId);

        if (activeCart.isEmpty()) {
            Cart newCart = cartRepository.save(cartMapper.createCart(clientId));

            cartItemRepository.save(cartMapper.createCartItem(newCart.getId(), product));
        } else {
            CartItem cartItem = cartItemRepository.findByCartIdAndProductId(activeCart.get().getId(), productId);
            activeCart.get().setUpdatedAt(LocalDateTime.now());
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
    public void updateProductInCartWhenRemoveProduct(Long cartId, Long productId, Integer deleteAll) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new StoreException("Cart not found by id: " + cartId));
        CartItem cartItem = cartItemRepository.findByCartIdAndProductIdAndStatusActive(cartId, productId)
                .orElseThrow(() -> new StoreException("CartItem is not exists"));

        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);

        cartMapper.updateCartItemWhenRemoveProduct(cartItem, deleteAll);
        cartItemRepository.save(cartItem);
    }

    public CartDetail getCartDetailByUserId(Long userId) {

        Optional<Cart> cart = cartRepository.findByClientIdAndActiveCart(userId);

        if (cart.isEmpty()) {
            return null;
        }

        List<CartItemDetail> cartItemDetailList = customCartItemRepository.getItemActiveInCart(cart.get().getId(), userId);
        CartDetail cartDetail = cartMapper.createCartDetail(cart.get().getId(), cartItemDetailList);

        return cartDetail;
    }
}
