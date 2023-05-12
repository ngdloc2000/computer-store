package com.cdtn.computerstore.dto.product.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ProductQuerySearchFormByAdmin {
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
    private List<Integer> brandList;
    private List<Integer> laptopSeriesList;
    private List<Integer> colorList;
    private List<Integer> cpuSeriesList;
    private List<Integer> ramCapacityList;
}
