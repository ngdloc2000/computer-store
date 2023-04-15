package com.cdtn.computerstore.dto.category.mapper;

import com.cdtn.computerstore.dto.category.request.CategoryCreationForm;
import com.cdtn.computerstore.entity.Category;
import com.cdtn.computerstore.enums.CategoryEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CategoryMapper {

    public Category createCategory(CategoryCreationForm form) {

        return Category.builder()
                .name(form.getCategoryName())
                .description(form.getDescription())
                .status(CategoryEnum.Status.checkValue(form.getStatus()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public void updateCategory(Category category, CategoryCreationForm form) {

        category.setName(form.getCategoryName());
        category.setDescription(form.getDescription());
        category.setStatus(CategoryEnum.Status.checkValue(form.getStatus()));
        category.setUpdatedAt(LocalDateTime.now());
    }
}
