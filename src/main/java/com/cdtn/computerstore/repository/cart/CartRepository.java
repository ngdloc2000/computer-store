package com.cdtn.computerstore.repository.cart;

import com.cdtn.computerstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "select * from cart where client_id = :clientId and status = 1", nativeQuery = true)
    Cart findByClientIdAndActiveCart(@Param("clientId") Long clientId);
}
