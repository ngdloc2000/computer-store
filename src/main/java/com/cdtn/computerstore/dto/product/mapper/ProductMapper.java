package com.cdtn.computerstore.dto.product.mapper;

import com.cdtn.computerstore.dto.product.request.ProductCreationForm;
import com.cdtn.computerstore.entity.Product;
import com.cdtn.computerstore.enums.ProductEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    public Product createProduct(ProductCreationForm form) {

        return Product.builder()
                .categoryId(form.getCategoryId())
                .name(form.getName())
                .imageMain(form.getImageMain())
                .brand(ProductEnum.Brand.checkValue(form.getBrand()))
                .description(form.getDescription())
                .price(form.getPrice())
                .discount(form.getDiscount())
                .quantity(form.getQuantity())
                .sold(0)
                .status(ProductEnum.Status.ACTIVE.getValue())
                .featured(ProductEnum.Featured.checkValue(form.getFeatured()))
                .warranty(form.getWarranty())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public void updateProduct(Product product, ProductCreationForm form) {

        product.setCategoryId(form.getCategoryId());
        product.setName(form.getName());
        product.setImageMain(form.getImageMain());
        product.setBrand(ProductEnum.Brand.checkValue(form.getBrand()));
        product.setDescription(form.getDescription());
        product.setPrice(form.getPrice());
        product.setDiscount(form.getDiscount());
        product.setQuantity(form.getQuantity());
        product.setStatus(ProductEnum.Status.ACTIVE.getValue());
        product.setFeatured(ProductEnum.Featured.checkValue(form.getFeatured()));
        product.setWarranty(form.getWarranty());
        product.setUpdatedAt(LocalDateTime.now());
    }
}
