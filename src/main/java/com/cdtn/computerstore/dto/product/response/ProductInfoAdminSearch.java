package com.cdtn.computerstore.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoAdminSearch {
    private Long productId;
    private String productName;
    private String imageMain;
    private String brand;
    private Long price;
    private Double discount;
    private Integer quantity;
    private Integer sold;
    private Integer status;
    private Integer featured;
    private Integer warranty;
    private LocalDateTime createdAt;
}
