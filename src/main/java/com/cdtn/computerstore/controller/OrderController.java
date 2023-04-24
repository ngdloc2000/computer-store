package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.dto.order.request.CreationOrderForm;
import com.cdtn.computerstore.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseData> createOrder(@RequestBody @Valid CreationOrderForm form) {

        try {
            Long orderId = orderService.createOrder(form);

            return ResponseEntity.ok(new BaseResponseData(200, "Success", orderId));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<BaseResponseData> payment(@RequestParam @NotNull boolean isPaid,
                                                    @RequestParam @NotNull Long orderId) {

        try {
            orderService.payment(isPaid, orderId);
            return ResponseEntity.ok(new BaseResponseData(200, "Error", orderId));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }
}
