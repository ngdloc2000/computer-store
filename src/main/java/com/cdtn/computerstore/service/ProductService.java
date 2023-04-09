package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.dto.product.mapper.ProductMapper;
import com.cdtn.computerstore.dto.product.request.ProductCreationForm;
import com.cdtn.computerstore.dto.specification.mapper.SpecificationMapper;
import com.cdtn.computerstore.entity.Product;
import com.cdtn.computerstore.entity.Specification;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.product.ProductRepository;
import com.cdtn.computerstore.repository.specification.SpecificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SpecificationRepository specificationRepository;
    private final ProductMapper productMapper;
    private final SpecificationMapper specificationMapper;

    @Transactional
    public BaseResponseData updateProduct(ProductCreationForm form) {

        try {
            Product product;
            Specification specification;

            if (Objects.isNull(form.getProductId())) {
                product = productMapper.createProduct(form);
                specification = specificationMapper.createSpecification(form);
            } else {
                product = productRepository.findById(form.getProductId())
                        .orElseThrow(() -> new StoreException("Product not found with id " + form.getProductId()));
                productMapper.updateProduct(product, form);

                specification = specificationRepository.findByProductId(product.getId());
                specificationMapper.updateSpecification(specification, form);
            }

            productRepository.save(product);
            specificationRepository.save(specification);

            return new BaseResponseData(200, "Success", null);
        } catch (Exception e) {
            return new BaseResponseData(500, "Error", e.getMessage());
        }
    }
}
