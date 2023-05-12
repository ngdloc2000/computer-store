package com.cdtn.computerstore.repository.product;

import com.cdtn.computerstore.dto.product.request.ProductQuerySearchFormByClient;
import com.cdtn.computerstore.dto.product.response.ProductInfoAdminSearch;
import com.cdtn.computerstore.dto.product.request.ProductQuerySearchFormByAdmin;
import com.cdtn.computerstore.dto.product.response.ProductInfoClientSearch;
import org.springframework.data.domain.Page;

public interface CustomProductRepository {

    Page<ProductInfoAdminSearch> getProductInfoAdminSearchDto(ProductQuerySearchFormByAdmin form);

    Page<ProductInfoClientSearch> getProductInfoClientSearchDto(ProductQuerySearchFormByClient form);
}
