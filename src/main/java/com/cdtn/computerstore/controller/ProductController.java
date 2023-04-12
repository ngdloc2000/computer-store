package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.dto.product.request.ProductCreationForm;
import com.cdtn.computerstore.dto.product.request.ProductQuerySearchForm;
import com.cdtn.computerstore.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseData> createCategory(@RequestBody @Valid ProductCreationForm creationForm) {

        return ResponseEntity.ok(productService.createProduct(creationForm));
    }

    @GetMapping("/admin/search")
    public ResponseEntity<BaseResponseData> searchProductByAdmin(
            @ModelAttribute @Valid ProductQuerySearchForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String errorMessage = "Bad Request: " + bindingResult.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseData(400, errorMessage, null));
        }

        return ResponseEntity.ok(productService.getProductInfoAdminSearchList(form));
    }
}
