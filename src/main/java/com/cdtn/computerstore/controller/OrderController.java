package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.dto.order.request.OrderCreationForm;
import com.cdtn.computerstore.dto.order.response.OrderDetail;
import com.cdtn.computerstore.dto.order.response.OrderInfoAdminSearch;
import com.cdtn.computerstore.dto.order.response.OrderInfoClientSearch;
import com.cdtn.computerstore.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseData> createOrder(@RequestBody @Valid OrderCreationForm form) {

        try {
            Long orderId = orderService.createOrder(form);

            return ResponseEntity.ok(new BaseResponseData(200, "Success", orderId));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<BaseResponseData> payment(@RequestParam boolean isPaid,
                                                    @RequestParam Long orderId) {

        try {
            orderService.payment(isPaid, orderId);
            return ResponseEntity.ok(new BaseResponseData(200, "Success", orderId));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }

    @GetMapping("/client/getAllOrder")
    public ResponseEntity<BaseResponseData> getAllOrderByClient(@RequestParam Long userId,
                                                                @RequestParam(required = false) Integer orderStatus,
                                                                @RequestParam Integer page,
                                                                @RequestParam Integer size) {

        try {
            List<OrderInfoClientSearch> orderList = orderService.getAllOrderByClient(userId, orderStatus, page, size);
            return ResponseEntity.ok(new BaseResponseData(200, "Success", orderList));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }

    @GetMapping("/admin/getAllOrder")
    public ResponseEntity<BaseResponseData> getAllOrderByAdmin(@RequestParam(required = false) Integer orderStatus,
                                                               @RequestParam(required = false) String fromDate,
                                                               @RequestParam(required = false) String toDate,
                                                               @RequestParam Integer page,
                                                               @RequestParam Integer size) {

        try {
            List<OrderInfoAdminSearch> orderList = orderService.getAllOrderByAdmin(orderStatus, fromDate, toDate, page, size);
            return ResponseEntity.ok(new BaseResponseData(200, "Success", orderList));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponseData> getDetail(@RequestParam Long orderId) {

        try {
            OrderDetail orderDetail = orderService.getOrderDetail(orderId);
            return ResponseEntity.ok(new BaseResponseData(200, "Success", orderDetail));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }
}
