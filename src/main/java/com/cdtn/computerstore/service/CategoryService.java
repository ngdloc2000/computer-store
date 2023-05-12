package com.cdtn.computerstore.service;

import com.cdtn.computerstore.common.Constant;
import com.cdtn.computerstore.dto.category.mapper.CategoryMapper;
import com.cdtn.computerstore.dto.category.request.CategoryCreationForm;
import com.cdtn.computerstore.entity.Category;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.category.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public void createCategory(CategoryCreationForm form) {

        Category category;

        if (Objects.isNull(form.getCategoryId())) {
            category = categoryMapper.createCategory(form);
        } else {
            category = categoryRepository.findById(form.getCategoryId())
                    .orElseThrow(() -> new StoreException("Category not found with id " + form.getCategoryId()));
            categoryMapper.updateCategory(category, form);
        }

        categoryRepository.save(category);
    }

    public Page<Category> showAll(int page, int size, String sort, String order) {

        Pageable pageable = null;
        if (order.equals(Constant.DESC)) {
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        } else if (order.equals(Constant.ASC)) {
            pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        }

        return categoryRepository.findAll(Objects.requireNonNull(pageable));
    }

    public List<Category> getAll() {

        return categoryRepository.findAll();
    }

}
