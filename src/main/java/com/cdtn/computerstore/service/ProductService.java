package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.product.mapper.ProductMapper;
import com.cdtn.computerstore.dto.product.request.ProductCreationForm;
import com.cdtn.computerstore.dto.product.response.ProductDetail;
import com.cdtn.computerstore.dto.product.response.ProductInfoAdminSearch;
import com.cdtn.computerstore.dto.product.request.ProductQuerySearchForm;
import com.cdtn.computerstore.dto.product.response.ProductInfoClientSearch;
import com.cdtn.computerstore.dto.specification.mapper.SpecificationMapper;
import com.cdtn.computerstore.entity.Category;
import com.cdtn.computerstore.entity.Product;
import com.cdtn.computerstore.entity.Specification;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.category.CategoryRepository;
import com.cdtn.computerstore.repository.product.CustomProductRepositoryImpl;
import com.cdtn.computerstore.repository.product.ProductRepository;
import com.cdtn.computerstore.repository.specification.SpecificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SpecificationRepository specificationRepository;
    private final CustomProductRepositoryImpl customProductRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final SpecificationMapper specificationMapper;

    @Transactional
    public void createProduct(ProductCreationForm form) {

        form.validateForm();

        Product product;
        Specification specification;

        if (Objects.isNull(form.getProductId())) {
            product = productRepository.save(productMapper.createProduct(form));
            specification = specificationMapper.createSpecification(product.getId(), form);
        } else {
            product = productRepository.findById(form.getProductId())
                    .orElseThrow(() -> new StoreException("Product not found with id " + form.getProductId()));
            productMapper.updateProduct(product, form);

            specification = specificationRepository.findByProductId(product.getId())
                    .orElseThrow(() -> new StoreException("Specification not found with product id " + form.getProductId()));
            specificationMapper.updateSpecification(specification, form);

            productRepository.save(product);
        }

        specificationRepository.save(specification);
    }

    public List<ProductInfoAdminSearch> getProductInfoAdminSearchList(ProductQuerySearchForm form) {

        Page<ProductInfoAdminSearch> productInfoPage = customProductRepository.getProductInfoAdminSearchDto(form);
        List<ProductInfoAdminSearch> productInfoList = productInfoPage.getContent();

        return productInfoList;
    }

    public List<ProductInfoClientSearch> getProductInfoClientSearchList(ProductQuerySearchForm form) {

        Page<ProductInfoClientSearch> productInfoPage = customProductRepository.getProductInfoClientSearchDto(form);
        List<ProductInfoClientSearch> productInfoList = productInfoPage.getContent();

        return productInfoList;
    }

    public ProductDetail findProductDetailByProductId(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new StoreException("Product not found with id " + productId));
        Specification specification = specificationRepository.findByProductId(productId)
                .orElseThrow(() -> new StoreException("Specification not found with product id " + productId));
        Category category = categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new StoreException("Category not found with id " + product.getCategoryId()));
        ProductDetail productDetail = productMapper.createProductDetail(product, specification, category);

        return productDetail;
    }

    @Transactional
    public void deleteProductById(Long productId) {

        Specification specification = specificationRepository.findByProductId(productId)
                .orElseThrow(() -> new StoreException("Specification not found with product id " + productId));
        specificationRepository.deleteById(specification.getId());
        productRepository.deleteById(productId);
    }
}
