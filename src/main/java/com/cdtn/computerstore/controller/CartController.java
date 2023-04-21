package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.service.CartService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    @PostMapping("/addProduct")
    public ResponseEntity<BaseResponseData> addProduct(@RequestParam @NotNull Long clientId,
                                                       @RequestParam @NotNull Long productId) {

        try {

            Map<String, Object> result = new HashMap<>();
            Long cartId = cartService.getCartIdAfterCreateCart(clientId, productId);

            result.put("cartId", cartId);

            return ResponseEntity.ok(new BaseResponseData(200, "Success", result));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }

    @PostMapping("/removeProduct")
    public ResponseEntity<BaseResponseData> removeProduct(@RequestParam @NotNull Long cartId,
                                                          @RequestParam @NotNull Long productId) {

        try {

            Map<String, Object> result = new HashMap<>();

            cartService.updateProductInCartWhenRemoveProduct(cartId, productId);

            result.put("cartId", cartId);

            return ResponseEntity.ok(new BaseResponseData(200, "Success", result));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }
}
