package com.cdtn.computerstore.dto.category.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryCreationForm {

    @NotBlank
    private String categoryName;

    private String description;
}
