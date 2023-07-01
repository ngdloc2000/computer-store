package com.cdtn.computerstore.repository.cartItem;

import com.cdtn.computerstore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndProductId(Long cartId, Long productId);

    @Query(value = "select * from cart_item ci where ci.cart_id = :cartId and ci.product_id = :productId and ci.status = 1 and ci.quantity > 0", nativeQuery = true)
    Optional<CartItem> findByCartIdAndProductIdAndStatusActive(@Param("cartId") Long cartId, @Param("productId") Long productId);


}
