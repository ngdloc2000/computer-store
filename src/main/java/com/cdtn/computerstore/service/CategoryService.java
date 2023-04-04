package com.cdtn.computerstore.service;

import com.cdtn.computerstore.common.Constant;
import com.cdtn.computerstore.dto.category.request.CategoryCreationForm;
import com.cdtn.computerstore.entity.Category;
import com.cdtn.computerstore.enums.category.StatusCategoryEnum;
import com.cdtn.computerstore.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryCreationForm creationForm) {

        Category category = Category.builder()
                .name(creationForm.getCategoryName())
                .description(creationForm.getDescription())
                .status(StatusCategoryEnum.ACTIVE.getValue())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        categoryRepository.save(category);
    }

    public Page<Category> getAll(int page, int size, String sort, String order) {

        Pageable pageable = null;
        if (order.equals(Constant.DESC)) {
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        } else if (order.equals(Constant.ASC)) {
            pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        }

        return categoryRepository.findAll(pageable);
    }

}
