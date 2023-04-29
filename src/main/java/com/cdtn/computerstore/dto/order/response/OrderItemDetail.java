package com.cdtn.computerstore.dto.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDetail {
    private Long productId;
    private String productName;
    private Integer itemQuantity;
    private Long itemPrice;
    private List<String> imageList;
}
