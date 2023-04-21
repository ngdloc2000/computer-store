package com.cdtn.computerstore.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoClientSearch {
    private Long productId;
    private Long categoryId;
    private String productName;
    private String imageMain;
    private String brand;
    private Long retailPrice;
    private Long latestPrice;
    private Double discount;
    private Integer warranty;
}
