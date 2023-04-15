package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.common.Constant;
import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.dto.category.request.CategoryCreationForm;
import com.cdtn.computerstore.entity.Category;
import com.cdtn.computerstore.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseData> createCategory(@RequestBody @Valid CategoryCreationForm creationForm) {

        try {
            categoryService.createCategory(creationForm);
            return ResponseEntity.ok(new BaseResponseData(200, "Success", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(200, "Success", e.getMessage()));
        }
    }

    @GetMapping("/showAll")
    public ResponseEntity<BaseResponseData> showAll(@RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "5") Integer size,
                                                    @RequestParam(defaultValue = "id") String sort,
                                                    @RequestParam(defaultValue = Constant.DESC) String order) {

        Page<Category> categories = categoryService.getAll(page, size, sort, order);
        return ResponseEntity.ok(new BaseResponseData(200, "Success", categories.getContent()));
    }
}
