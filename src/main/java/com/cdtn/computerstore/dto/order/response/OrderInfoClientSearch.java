package com.cdtn.computerstore.dto.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoClientSearch {
    private Long orderId;
    private Long totalPrice;
    private Integer totalProduct;
    private Integer status;
    private LocalDateTime createAt;
    private LocalDateTime canceledAt;
    private LocalDateTime completedAt;
}
