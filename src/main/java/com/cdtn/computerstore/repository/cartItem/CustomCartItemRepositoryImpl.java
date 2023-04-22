package com.cdtn.computerstore.repository.cartItem;

import com.cdtn.computerstore.dto.cart.response.CartItemDetail;
import com.cdtn.computerstore.exception.StoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class CustomCartItemRepositoryImpl implements CustomCartItemRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<CartItemDetail> getItemInCart(Long cartId, Long clientId) {

        String query =
                """
                        select ci.product_id                  as productId,
                               p.name                         as productName,
                               p.image_main                   as productImageMain,
                               ci.quantity                    as itemQuantity,
                               p.latest_price                 as productLatestPrice,
                               (ci.quantity * p.latest_price) as totalPricePerProduct
                        from cart c
                                 join cart_item ci on c.id = ci.cart_id
                                 join product p on ci.product_id = p.id
                        where c.client_id = :clientId
                          and c.id = :cartId
                          and c.status = 1
                          and ci.status = 1""";

        try {
            List<CartItemDetail> cartItemDetailList = jdbcTemplate.query(
                    query,
                    setParamGetItemInCart(cartId, clientId),
                    (rs, rowNum) -> CartItemDetail.builder()
                            .productId(rs.getLong("productId"))
                            .productName(rs.getString("productName"))
                            .productImageMain(rs.getString("productImageMain"))
                            .itemQuantity(rs.getLong("itemQuantity"))
                            .productLatestPrice(rs.getLong("productLatestPrice"))
                            .totalPricePerProduct(rs.getLong("totalPricePerProduct"))
                            .build()
            );

            return cartItemDetailList;
        } catch (Exception e) {
            throw new StoreException(e.getMessage());
        }
    }

    private Map<String, Object> setParamGetItemInCart(Long cartId, Long clientId) {

        Map<String, Object> map = new HashMap<>();

        if (Objects.nonNull(cartId)) {
            map.put("cartId", cartId);
        }

        if (Objects.nonNull(clientId)) {
            map.put("clientId", clientId);
        }

        return map;
    }
}
