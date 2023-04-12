package com.cdtn.computerstore.repository.product;

import com.cdtn.computerstore.dto.product.response.ProductInfoAdminSearch;
import com.cdtn.computerstore.dto.product.request.ProductQuerySearchForm;
import org.springframework.data.domain.Page;

public interface CustomProductRepository {

    Page<ProductInfoAdminSearch> getProductInfoAdminSearchDto(ProductQuerySearchForm form);
}
