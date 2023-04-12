package com.cdtn.computerstore.repository.product;

import com.cdtn.computerstore.dto.product.response.ProductInfoAdminSearch;
import com.cdtn.computerstore.dto.product.request.ProductQuerySearchForm;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.util.PageUtil;
import com.cdtn.computerstore.util.QueryUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Page<ProductInfoAdminSearch> getProductInfoAdminSearchDto(ProductQuerySearchForm form) {

        try {
            List<ProductInfoAdminSearch> productInfoAdminSearchList = jdbcTemplate.query(
                    createQueryGetProductDto(form),
                    setParam(form),
                    (rs, rowNum) -> new ProductInfoAdminSearch(
                            rs.getLong("productId"),
                            rs.getLong("categoryId"),
                            rs.getString("productName"),
                            rs.getString("p.image_main"),
                            rs.getInt("p.brand"),
                            rs.getLong("p.price"),
                            rs.getDouble("p.discount"),
                            rs.getInt("p.quantity"),
                            rs.getInt("p.sold"),
                            rs.getInt("p.status"),
                            rs.getInt("p.featured"),
                            rs.getInt("p.warranty"),
                            LocalDateTime.parse(rs.getString("createAt"), dateTimeFormatter)
                    )
            );

            return PageUtil.getPage(productInfoAdminSearchList, form.getPage(), form.getSize());
        } catch (Exception e) {
            throw new StoreException(e.getMessage());
        }
    }

    private String createQueryGetProductDto(ProductQuerySearchForm form) {

        String select =
                """
                        select p.id          as productId,
                               p.category_id as categoryId,
                               p.name        as productName,
                               p.image_main,
                               p.brand,
                               p.price,
                               p.discount,
                               p.quantity,
                               p.sold,
                               p.status,
                               p.featured,
                               p.warranty,
                               p.created_at  as createAt
                        from product p
                                 join specification s on p.id = s.product_id \s""";

        List<String> whereList = new ArrayList<>();
        StringBuilder where = new StringBuilder();

        if (Objects.nonNull(form.getCategoryId())) {
            whereList.add("p.category_id = :categoryId ");
        }

        if (StringUtils.isNotBlank(form.getProductName())) {
            whereList.add("p.name like '%:productName%' ");
        }

        if (Objects.nonNull(form.getMinPrice()) && Objects.nonNull(form.getMaxPrice())) {
            whereList.add("p.price >= :minPrice and p.price <= :maxPrice ");
        } else if (Objects.nonNull(form.getMinPrice())) {
            whereList.add("p.price >= :minPrice ");
        } else if (Objects.nonNull(form.getMaxPrice())) {
            whereList.add("p.price <= :maxPrice ");
        }

        if (Objects.nonNull(form.getStatus())) {
            whereList.add("p.status = :status ");
        }

        if (Objects.nonNull(form.getFeatured())) {
            whereList.add("p.featured = :featured ");
        }

        if (StringUtils.isNotBlank(form.getFromDate()) && StringUtils.isNotBlank(form.getToDate())) {
            whereList.add("p.created_at >= :fromDate and p.created_at <= :toDate ");
        } else if (StringUtils.isNotBlank(form.getFromDate())) {
            whereList.add("p.created_at >= :fromDate ");
        } else if (StringUtils.isNotBlank(form.getToDate())) {
            whereList.add("p.created_at <= :toDate ");
        }

        if (whereList.size() != 0) {
            where.append("where ");
            if (whereList.size() == 1) {
                where.append(whereList.get(0));
            } else {
                for (int i = 0; i < whereList.size() - 1; i++) {
                    where.append(whereList.get(i)).append(" and ");
                }
            }
        }

        String order = " order by ";
        if (StringUtils.isNotBlank(form.getOrder())) {
            order += QueryUtil.checkOrderSearchProductByAdmin(form.getOrder());
        } else {
            order += "p.id ";
        }

        if (StringUtils.isNotBlank(form.getSort())) {
            order += form.getSort();
        } else {
            order += "desc";
        }

        String query = select + where + order;

        return query;
    }

    private Map<String, Object> setParam(ProductQuerySearchForm form) {

        Map<String, Object> map = new HashMap<>();

        if (Objects.nonNull(form.getCategoryId())) {
            map.put("categoryId", form.getCategoryId());
        }

        if (StringUtils.isNotBlank(form.getProductName())) {
            map.put("productName", form.getProductName());
        }

        if (Objects.nonNull(form.getMinPrice())) {
            map.put("minPrice", form.getMinPrice());
        }

        if (Objects.nonNull(form.getMaxPrice())) {
            map.put("maxPrice", form.getMaxPrice());
        }

        if (Objects.nonNull(form.getStatus())) {
            map.put("status", form.getStatus());
        }

        if (Objects.nonNull(form.getFeatured())) {
            map.put("featured", form.getFeatured());
        }

        if (StringUtils.isNotBlank(form.getFromDate())) {
            map.put("fromDate", form.getFromDate());
        }

        if (StringUtils.isNotBlank(form.getToDate())) {
            String toDate = form.getToDate() + " 23:59:59";
            map.put("toDate", toDate);
        }

        return map;
    }
}
