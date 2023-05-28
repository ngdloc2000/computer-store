package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.dto.product.request.ProductCreationForm;
import com.cdtn.computerstore.dto.product.request.ProductQuerySearchFormByAdmin;
import com.cdtn.computerstore.dto.product.request.ProductQuerySearchFormByClient;
import com.cdtn.computerstore.dto.product.response.ProductDetail;
import com.cdtn.computerstore.dto.product.response.ProductInfoAdminSearch;
import com.cdtn.computerstore.dto.product.response.ProductInfoClientSearch;
import com.cdtn.computerstore.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<BaseResponseData> createProduct(@RequestBody @Valid ProductCreationForm creationForm,
                                                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String errorMessage = "Bad Request: " + bindingResult.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseData(400, errorMessage, null));
        }

        try {
            productService.createProduct(creationForm);

            return ResponseEntity.ok(new BaseResponseData(200, "Success", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }

    @PostMapping("/admin/search")
    public ResponseEntity<BaseResponseData> searchProductByAdmin(
            @RequestBody @Valid ProductQuerySearchFormByAdmin form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String errorMessage = "Bad Request: " + bindingResult.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseData(400, errorMessage, null));
        }

        try {
            Page<ProductInfoAdminSearch> productList = productService.getProductInfoAdminSearchList(form);

            return ResponseEntity.ok(new BaseResponseData(200, "Success", productList));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }

    @PostMapping("/client/search")
    public ResponseEntity<BaseResponseData> searchProductByClient(
            @RequestBody @Valid ProductQuerySearchFormByClient form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String errorMessage = "Bad Request: " + bindingResult.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseData(400, errorMessage, null));
        }

        try {
            Page<ProductInfoClientSearch> productList = productService.getProductInfoClientSearchList(form);

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

    @PostMapping("/delete")
    public ResponseEntity<BaseResponseData> deleteProduct(@RequestParam Long productId) {

        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new BaseResponseData(200, "Success", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponseData(500, "Error", e.getMessage()));
        }
    }
}
