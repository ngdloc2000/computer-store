package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.dto.product.request.ProductCreationForm;
import com.cdtn.computerstore.dto.product.request.ProductQuerySearchForm;
import com.cdtn.computerstore.dto.product.response.ProductDetail;
import com.cdtn.computerstore.dto.product.response.ProductInfoAdminSearch;
import com.cdtn.computerstore.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseData> createProduct(@RequestBody @Valid ProductCreationForm creationForm) {

        try {
            productService.createProduct(creationForm);

            return ResponseEntity.ok(new BaseResponseData(200, "Success", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
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

        try {
            List<ProductInfoAdminSearch> productList = productService.getProductInfoAdminSearchList(form);

            return ResponseEntity.ok(new BaseResponseData(200, "Success", productList));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponseData> getProductDetailById(@RequestParam Long productId) {

        try {
            ProductDetail productDetail = productService.findProductDetailByProductId(productId);

            return ResponseEntity.ok(new BaseResponseData(200, "Success", productDetail));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponseData> deleteProduct(@RequestParam Long productId) {

        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new BaseResponseData(200, "Success", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }
}