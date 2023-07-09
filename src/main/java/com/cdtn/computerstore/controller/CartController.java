package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.dto.cart.response.CartDetail;
import com.cdtn.computerstore.service.CartService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @PostMapping("/addProduct")
    public ResponseEntity<BaseResponseData> addProduct(@RequestParam @NotNull Long clientId,
                                                       @RequestParam @NotNull Long productId) {

        try {

            cartService.createCartWhenPickProduct(clientId, productId);

            return ResponseEntity.ok(new BaseResponseData(200, "Success", null));
        } catch (Exception e) {
            LOGGER.error("Exception when /cart/addProduct", e);
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }

    @PostMapping("/removeProduct")
    public ResponseEntity<BaseResponseData> removeProduct(@RequestParam @NotNull Long cartId,
                                                          @RequestParam @NotNull Long productId,
                                                          @RequestParam(required = false, defaultValue = "0") Integer deleteAll) {

        try {

            cartService.updateProductInCartWhenRemoveProduct(cartId, productId, deleteAll);

            return ResponseEntity.ok(new BaseResponseData(200, "Success", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }

    @GetMapping("/getCartDetail")
    public ResponseEntity<BaseResponseData> getCartDetailByUserId(@RequestParam @NotNull Long userId) {

        try {

            CartDetail cartDetail = cartService.getCartDetailByUserId(userId);

            return ResponseEntity.ok(new BaseResponseData(200, "Success", cartDetail));
        } catch (Exception e) {
            LOGGER.error("Exception when /cart/getCartDetail", e);
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }
}
