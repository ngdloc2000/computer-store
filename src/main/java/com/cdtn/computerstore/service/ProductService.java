package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.asset.mapper.AssetMapper;
import com.cdtn.computerstore.dto.product.mapper.ProductMapper;
import com.cdtn.computerstore.dto.product.request.ProductCreationForm;
import com.cdtn.computerstore.dto.product.request.ProductQuerySearchFormByClient;
import com.cdtn.computerstore.dto.product.response.ProductDetail;
import com.cdtn.computerstore.dto.product.response.ProductInfoAdminSearch;
import com.cdtn.computerstore.dto.product.request.ProductQuerySearchFormByAdmin;
import com.cdtn.computerstore.dto.product.response.ProductInfoClientSearch;
import com.cdtn.computerstore.dto.specification.mapper.SpecificationMapper;
import com.cdtn.computerstore.entity.Asset;
import com.cdtn.computerstore.entity.Category;
import com.cdtn.computerstore.entity.Product;
import com.cdtn.computerstore.entity.Specification;
import com.cdtn.computerstore.enums.ProductEnum;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.asset.AssetRepository;
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
    private final AssetRepository assetRepository;
    private final ProductMapper productMapper;
    private final SpecificationMapper specificationMapper;
    private final AssetMapper assetMapper;

    @Transactional
    public void createProduct(ProductCreationForm form) {

        form.validateForm();

        Product product;
        Specification specification;
        List<Asset> assetList;

        if (Objects.isNull(form.getProductId())) {
            product = productRepository.save(productMapper.createProduct(form));
            specification = specificationMapper.createSpecification(product.getId(), form);
            assetList = assetMapper.createAsset(product.getId(), form.getImageList());
        } else {
            product = productRepository.findById(form.getProductId())
                    .orElseThrow(() -> new StoreException("Product not found with id " + form.getProductId()));
            productMapper.updateProduct(product, form);
            productRepository.save(product);

            specification = specificationRepository.findByProductId(product.getId())
                    .orElseThrow(() -> new StoreException("Specification not found with product id " + form.getProductId()));
            specificationMapper.updateSpecification(specification, form);

            assetList = assetRepository.findAllByProductId(form.getProductId());
            if(assetList.size() > 0) {
                assetRepository.deleteAll(assetList);
            }
            assetList = assetMapper.createAsset(form.getProductId(), form.getImageList());
//            assetRepository.saveAll(assetMapper.createAsset(form.getProductId(), form.getImageList()));
        }

        specificationRepository.save(specification);
        assetRepository.saveAll(assetList);
    }

    public Page<ProductInfoAdminSearch> getProductInfoAdminSearchList(ProductQuerySearchFormByAdmin form) {

        Page<ProductInfoAdminSearch> productInfoPage = customProductRepository.getProductInfoAdminSearchDto(form);

        return productInfoPage;
    }

    public Page<ProductInfoClientSearch> getProductInfoClientSearchList(ProductQuerySearchFormByClient form) {

        Page<ProductInfoClientSearch> productInfoPage = customProductRepository.getProductInfoClientSearchDto(form);

        return productInfoPage;
    }

    public ProductDetail findProductDetailByProductId(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new StoreException("Product not found with id " + productId));
        Specification specification = specificationRepository.findByProductId(productId)
                .orElseThrow(() -> new StoreException("Specification not found with product id " + productId));
        Category category = categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new StoreException("Category not found with id " + product.getCategoryId()));
        List<String> imageLinkProductList = assetRepository.findAllImageLinkProduct(productId);
        ProductDetail productDetail = productMapper.createProductDetail(product, specification, category, imageLinkProductList);

        return productDetail;
    }

    @Transactional
    public void deleteProductById(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new StoreException("Product not found with id: " + productId));
        product.setStatus(ProductEnum.Status.INACTIVE.getValue());
        productRepository.save(product);
    }

    public Product checkProductValid(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new StoreException("Product not found with id: " + productId));

        if (!product.getStatus().equals(ProductEnum.Status.ACTIVE.getValue())) {
            throw new StoreException("Product status is not active");
        }

        return product;
    }
}
