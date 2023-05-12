package com.cdtn.computerstore.repository.product;

import com.cdtn.computerstore.dto.product.request.ProductQuerySearchFormByClient;
import com.cdtn.computerstore.dto.product.response.ProductInfoAdminSearch;
import com.cdtn.computerstore.dto.product.request.ProductQuerySearchFormByAdmin;
import com.cdtn.computerstore.dto.product.response.ProductInfoClientSearch;
import com.cdtn.computerstore.enums.ProductEnum;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.asset.AssetRepository;
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
    private final AssetRepository assetRepository;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Page<ProductInfoAdminSearch> getProductInfoAdminSearchDto(ProductQuerySearchFormByAdmin form) {

        try {
            List<ProductInfoAdminSearch> productInfoAdminSearchList = jdbcTemplate.query(
                    createGetProductQueryByAdmin(form),
                    setParamSearchProductByAdmin(form),
                    (rs, rowNum) -> new ProductInfoAdminSearch(
                            rs.getLong("productId"),
                            rs.getString("productName"),
                            ProductEnum.Brand.getNameByValue(rs.getInt("p.brand")),
                            rs.getLong("p.retail_price"),
                            rs.getLong("p.latest_price"),
                            rs.getDouble("p.discount"),
                            rs.getInt("p.quantity"),
                            rs.getInt("p.sold"),
                            rs.getInt("p.status"),
                            rs.getInt("p.featured"),
                            rs.getInt("p.warranty"),
                            LocalDateTime.parse(rs.getString("createAt"), dateTimeFormatter),
                            assetRepository.findAllImageLinkProduct(rs.getLong("productId"))
                    )
            );

            return PageUtil.getPage(productInfoAdminSearchList, form.getPage(), form.getSize());
        } catch (Exception e) {
            throw new StoreException(e.getMessage());
        }
    }

    @Override
    public Page<ProductInfoClientSearch> getProductInfoClientSearchDto(ProductQuerySearchFormByClient form) {

        try {
            List<ProductInfoClientSearch> productInfoClientSearchList = jdbcTemplate.query(
                    createGetProductQueryByClient(form),
                    setParamSearchProductByClient(form),
                    (rs, rowNum) -> ProductInfoClientSearch.builder()
                            .productId(rs.getLong("productId"))
                            .productName(rs.getString("productName"))
                            .brand(ProductEnum.Brand.getNameByValue(
                                    Objects.isNull(rs.getBigDecimal("p.brand"))
                                            ? null
                                            : rs.getBigDecimal("p.brand").intValue()))
                            .retailPrice(
                                    Objects.isNull(rs.getBigDecimal("p.retail_price"))
                                            ? null
                                            : rs.getBigDecimal("p.retail_price").longValue()
                            )
                            .latestPrice(
                                    Objects.isNull(rs.getBigDecimal("p.latest_price"))
                                            ? null
                                            : rs.getBigDecimal("p.latest_price").longValue()
                            )
                            .discount(
                                    Objects.isNull(rs.getBigDecimal("p.discount"))
                                            ? null
                                            : rs.getBigDecimal("p.discount").doubleValue())
                            .warranty(
                                    Objects.isNull(rs.getBigDecimal("p.warranty"))
                                            ? null
                                            : rs.getBigDecimal("p.warranty").intValue()
                            )
                            .imageList(assetRepository.findAllImageLinkProduct(rs.getLong("productId")))
                            .build()
            );

            return PageUtil.getPage(productInfoClientSearchList, form.getPage(), form.getSize());
        } catch (Exception e) {
            throw new StoreException(e.getMessage());
        }
    }

    private String createGetProductQueryByAdmin(ProductQuerySearchFormByAdmin form) {

        String select =
                """
                        select p.id          as productId,
                               p.category_id as categoryId,
                               p.name        as productName,
                               p.brand,
                               p.retail_price,
                               p.latest_price,
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

        if (Objects.nonNull(form.getCategoryId())) {
            whereList.add("p.category_id = :categoryId ");
        }

        if (StringUtils.isNotBlank(form.getProductName())) {
            whereList.add("p.name like :productName ");
        }

        if (Objects.nonNull(form.getMinPrice()) && Objects.nonNull(form.getMaxPrice())) {
            whereList.add("p.latest_price >= :minPrice and p.latest_price <= :maxPrice ");
        } else if (Objects.nonNull(form.getMinPrice())) {
            whereList.add("p.latest_price >= :minPrice ");
        } else if (Objects.nonNull(form.getMaxPrice())) {
            whereList.add("p.latest_price <= :maxPrice ");
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

        String where = "";
        if (whereList.size() != 0) {
            where = QueryUtil.createWhereQuery(whereList);
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

    private String createGetProductQueryByClient(ProductQuerySearchFormByClient form) {

        String select =
                """
                        select p.id          as productId,
                               p.category_id as categoryId,
                               p.name        as productName,
                               p.brand,
                               p.retail_price,
                               p.latest_price,
                               p.discount,
                               p.warranty,
                               p.created_at  as createAt
                        from product p
                                 join specification s on p.id = s.product_id
                                 join category c on c.id = p.category_id \s""";

        List<String> whereList = new ArrayList<>();

        whereList.add("c.status = 1 and p.status = 1 ");

        if (Objects.nonNull(form.getCategoryId())) {
            whereList.add("p.category_id = :categoryId ");
        }

        if (StringUtils.isNotBlank(form.getProductName())) {
            whereList.add("p.name like :productName ");
        }

        if (Objects.nonNull(form.getMinPrice()) && Objects.nonNull(form.getMaxPrice())) {
            whereList.add("p.latest_price >= :minPrice and p.latest_price <= :maxPrice ");
        } else if (Objects.nonNull(form.getMinPrice())) {
            whereList.add("p.latest_price >= :minPrice ");
        } else if (Objects.nonNull(form.getMaxPrice())) {
            whereList.add("p.latest_price <= :maxPrice ");
        }

        if (Objects.nonNull(form.getBrandList()) && form.getBrandList().size() != 0) {
            String brandWhere = "p.brand in (";
            whereList.add(brandWhere + QueryUtil.createInConditionQuery(form.getBrandList()));
        }

        if (Objects.nonNull(form.getLaptopSeriesList()) && form.getLaptopSeriesList().size() != 0) {
            String laptopSeriesWhere = "s.laptop_series in (";
            whereList.add(laptopSeriesWhere + QueryUtil.createInConditionQuery(form.getLaptopSeriesList()));
        }

        if (Objects.nonNull(form.getColorList()) && form.getColorList().size() != 0) {
            String colorWhere = "s.color in (";
            whereList.add(colorWhere + QueryUtil.createInConditionQuery(form.getColorList()));
        }

        if (Objects.nonNull(form.getCpuSeriesList()) && form.getCpuSeriesList().size() != 0) {
            String cpuSeriesWhere = "s.cpu_series in (";
            whereList.add(cpuSeriesWhere + QueryUtil.createInConditionQuery(form.getCpuSeriesList()));
        }

        if (Objects.nonNull(form.getRamCapacityList()) && form.getRamCapacityList().size() != 0) {
            String ramCapacityWhere = "s.ram_capacity in (";
            whereList.add(ramCapacityWhere + QueryUtil.createInConditionQuery(form.getRamCapacityList()));
        }

        String where = "";
        if (whereList.size() != 0) {
            where = QueryUtil.createWhereQuery(whereList);
        }

        String order = " order by ";
        if (StringUtils.isNotBlank(form.getOrder())) {
            order += QueryUtil.checkOrderSearchProductByClient(form.getOrder());
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

    private Map<String, Object> setParamSearchProductByAdmin(ProductQuerySearchFormByAdmin form) {

        Map<String, Object> map = new HashMap<>();

        if (Objects.nonNull(form.getCategoryId())) {
            map.put("categoryId", form.getCategoryId());
        }

        if (StringUtils.isNotBlank(form.getProductName())) {
            map.put("productName", "%" + form.getProductName() + "%");
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

    private Map<String, Object> setParamSearchProductByClient(ProductQuerySearchFormByClient form) {

        Map<String, Object> map = new HashMap<>();

        if (Objects.nonNull(form.getCategoryId())) {
            map.put("categoryId", form.getCategoryId());
        }

        if (StringUtils.isNotBlank(form.getProductName())) {
            map.put("productName", "%" + form.getProductName() + "%");
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
