package com.cdtn.computerstore.dto.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderDetail {
    private Long orderId;
    private Long clientId;
    private Integer status;
    private Long total;
    private String consigneeName;
    private String consigneePhoneNumber;
    private String deliveryAddress;
    private LocalDateTime createAt;
    private LocalDateTime canceledAt;
    private LocalDateTime completedAt;
    private List<OrderItemDetail> orderItemDetailList;
}
