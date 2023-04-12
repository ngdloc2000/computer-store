package com.cdtn.computerstore.dto.product.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductQuerySearchForm {
    private Long categoryId;
    private String productName;
    private Double minPrice;
    private Double maxPrice;
    private Integer status;
    private Integer featured;
    private String fromDate;
    private String toDate;
    @NotNull
    private Integer page;
    @NotNull
    private Integer size;
    private String sort;
    private String order;
}
