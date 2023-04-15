package com.cdtn.computerstore.dto.category.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryCreationForm {

    private Long categoryId;

    @NotBlank
    private String categoryName;

    private String description;

    private Integer status;
}
