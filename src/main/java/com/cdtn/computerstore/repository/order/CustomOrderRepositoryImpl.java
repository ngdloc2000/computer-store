package com.cdtn.computerstore.repository.order;

import com.cdtn.computerstore.dto.order.response.OrderDetail;
import com.cdtn.computerstore.dto.order.response.OrderInfoAdminSearch;
import com.cdtn.computerstore.dto.order.response.OrderInfoClientSearch;
import com.cdtn.computerstore.dto.order.response.OrderItemDetail;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.asset.AssetRepository;
import com.cdtn.computerstore.util.PageUtil;
import com.cdtn.computerstore.util.QueryUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final AssetRepository assetRepository;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Page<OrderInfoClientSearch> getAllOrderByClient(Long userId, Integer orderStatus, Integer page, Integer size) {

        try {
            List<OrderInfoClientSearch> orderList = jdbcTemplate.query(
                    createGetAllOrderQueryByClientQuery(userId, orderStatus),
                    setParameterGetAllOrderQueryByClient(userId, orderStatus),
                    (rs, rowNum) -> new OrderInfoClientSearch(
                            rs.getLong("orderId"),
                            rs.getLong("totalPrice"),
                            rs.getInt("totalProduct"),
                            rs.getInt("status"),
                            StringUtils.isBlank(rs.getString("createAt"))
                                    ? null
                                    : LocalDateTime.parse(rs.getString("createAt"), dateTimeFormatter),
                            StringUtils.isBlank(rs.getString("canceledAt"))
                                    ? null
                                    : LocalDateTime.parse(rs.getString("canceledAt"), dateTimeFormatter),
                            StringUtils.isBlank(rs.getString("completedAt"))
                                    ? null
                                    : LocalDateTime.parse(rs.getString("completedAt"), dateTimeFormatter),
                            rs.getString("checkoutSessionUrl")
                    )
            );

            return PageUtil.getPage(orderList, page, size);
        } catch (Exception e) {
            throw new StoreException(e.getMessage());
        }
    }

    @Override
    public Page<OrderInfoAdminSearch> getAllOrderByAdmin(Integer orderStatus,
                                                         String fromDate,
                                                         String toDate,
                                                         String search,
                                                         Integer page,
                                                         Integer size) {

        try {
            List<OrderInfoAdminSearch> orderList = jdbcTemplate.query(
                    createGetAllOrderQueryByAdminQuery(orderStatus, fromDate, toDate, search),
                    setParameterGetAllOrderQueryByAdmin(orderStatus, fromDate, toDate, search),
                    (rs, rowNum) -> new OrderInfoAdminSearch(
                            rs.getLong("orderId"),
                            rs.getLong("totalPrice"),
                            rs.getInt("totalProduct"),
                            rs.getInt("status"),
                            StringUtils.isBlank(rs.getString("createAt"))
                                    ? null
                                    : LocalDateTime.parse(rs.getString("createAt"), dateTimeFormatter),
                            StringUtils.isBlank(rs.getString("canceledAt"))
                                    ? null
                                    : LocalDateTime.parse(rs.getString("canceledAt"), dateTimeFormatter),
                            StringUtils.isBlank(rs.getString("completedAt"))
                                    ? null
                                    : LocalDateTime.parse(rs.getString("completedAt"), dateTimeFormatter),
                            rs.getString("clientName")
                    )
            );

            return PageUtil.getPage(orderList, page, size);
        } catch (Exception e) {
            throw new StoreException(e.getMessage());
        }
    }

    @Override
    public OrderDetail getOrderDetailByOrderId(Long orderId) {

        try {
            OrderDetail orderDetail = jdbcTemplate.queryForObject(
                    createGetOrderDetailByOrderIdQuery(orderId),
                    setParameterGetOrderDetailByOrderId(orderId),
                    (rs, rowNum) -> OrderDetail.builder()
                            .orderId(rs.getLong("orderId"))
                            .clientId(rs.getLong("clientId"))
                            .status(rs.getInt("status"))
                            .total(rs.getLong("total"))
                            .consigneeName(rs.getString("consigneeName"))
                            .consigneePhoneNumber(rs.getString("consigneePhoneNumber"))
                            .deliveryAddress(rs.getString("deliveryAddress"))
                            .createAt(StringUtils.isBlank(rs.getString("createAt"))
                                    ? null
                                    : LocalDateTime.parse(rs.getString("createAt"), dateTimeFormatter))
                            .canceledAt(StringUtils.isBlank(rs.getString("canceledAt"))
                                    ? null
                                    : LocalDateTime.parse(rs.getString("canceledAt"), dateTimeFormatter))
                            .completedAt(StringUtils.isBlank(rs.getString("completedAt"))
                                    ? null
                                    : LocalDateTime.parse(rs.getString("completedAt"), dateTimeFormatter))
                            .orderItemDetailList(getOrderItemDetailByOrderId(rs.getLong("orderId")))
                            .build()
            );

            return orderDetail;
        } catch (Exception e) {
            throw new StoreException(e.getMessage());
        }
    }

    @Override
    public List<OrderItemDetail> getOrderItemDetailByOrderId(Long orderId) {

        try {
            List<OrderItemDetail> orderItemDetailList = jdbcTemplate.query(
                    createGetOrderItemDetailQuery(orderId),
                    setParameterGetOrderItemDetailByOrderId(orderId),
                    (rs, rowNum) -> new OrderItemDetail(
                            rs.getLong("productId"),
                            rs.getString("productName"),
                            rs.getInt("itemQuantity"),
                            rs.getLong("itemPrice"),
                            assetRepository.findAllImageLinkProduct(rs.getLong("productId"))
                    )
            );

            return orderItemDetailList;
        } catch (Exception e) {
            throw new StoreException(e.getMessage());
        }
    }

    private String createGetOrderDetailByOrderIdQuery(Long orderId) {

        String select = """
                select o.id                     as orderId,
                       o.client_id              as clientId,
                       o.status                 as status,
                       o.total                  as total,
                       o.consignee_name         as consigneeName,
                       o.consignee_phone_number as consigneePhoneNumber,
                       o.delivery_address       as deliveryAddress,
                       o.create_at              as createAt,
                       o.canceled_at            as canceledAt,
                       o.completed_at           as completedAt
                from orders o\040""";

        List<String> whereList = new ArrayList<>();
        String where = "";

        if (Objects.nonNull(orderId)) {
            whereList.add("o.id = :orderId ");
        }

        if (whereList.size() != 0) {
            where = QueryUtil.createWhereQuery(whereList);
        }

        String query = select + where;

        return query;
    }

    private String createGetAllOrderQueryByClientQuery(Long userId,
                                                       Integer orderStatus) {

        String select = """
                select o.id                 as orderId,
                       o.total              as totalPrice,
                       (select sum(oi.quantity)
                        from orders o2
                                 join order_item oi on o2.id = oi.order_id
                        where o.id = o2.id) as totalProduct,
                       o.status             as status,
                       o.create_at          as createAt,
                       o.canceled_at        as canceledAt,
                       o.completed_at       as completedAt,
                       o.checkout_session_url as checkoutSessionUrl
                from orders o \s""";

        List<String> whereList = new ArrayList<>();

        if (Objects.nonNull(userId)) {
            whereList.add("o.client_id = :userId ");
        }

        if (Objects.nonNull(orderStatus)) {
            whereList.add("o.status = :orderStatus ");
        }

        String where = "";
        if (whereList.size() != 0) {
            where = QueryUtil.createWhereQuery(whereList);
        }

        String order = " order by o.id desc";

        String query = select + where + order;

        return query;
    }

    private String createGetAllOrderQueryByAdminQuery(Integer orderStatus,
                                                      String fromDate,
                                                      String toDate,
                                                      String search) {

        String select = """
                select o.id                 as orderId,
                       o.total              as totalPrice,
                       (select sum(oi.quantity)
                        from orders o2
                                 join order_item oi on o2.id = oi.order_id
                        where o.id = o2.id) as totalProduct,
                       o.status             as status,
                       o.create_at          as createAt,
                       o.canceled_at        as canceledAt,
                       o.completed_at       as completedAt,
                       c.full_name          as clientName
                from orders o join client c on c.user_id = o.client_id \s""";

        List<String> whereList = new ArrayList<>();

        if (Objects.nonNull(orderStatus)) {
            whereList.add("o.status = :orderStatus ");
        }
        if (StringUtils.isNotBlank(search)) {
            whereList.add("c.full_name like :search or o.id like :search ");
        }

        if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
            whereList.add("o.create_at >= :fromDate and o.create_at <= :toDate ");
        } else if (StringUtils.isNotBlank(fromDate)) {
            whereList.add("o.create_at >= :fromDate ");
        } else if (StringUtils.isNotBlank(toDate)) {
            whereList.add("o.create_at <= :toDate ");
        }

        String where = "";
        if (whereList.size() != 0) {
            where = QueryUtil.createWhereQuery(whereList);
        }

        String order = " order by o.id desc";

        String query = select + where + order;

        return query;
    }

    private String createGetOrderItemDetailQuery(Long orderId) {

        String select = """
                select oi.product_id as productId,
                       p.name        as productName,
                       oi.quantity   as itemQuantity,
                       oi.price      as itemPrice
                from order_item oi
                         join product p on oi.product_id = p.id\040""";

        List<String> whereList = new ArrayList<>();
        String where = "";

        if (Objects.nonNull(orderId)) {
            whereList.add("oi.order_id = :orderId ");
        }

        if (whereList.size() != 0) {
            where = QueryUtil.createWhereQuery(whereList);
        }

        String query = select + where;

        return query;
    }

    private Map<String, Object> setParameterGetAllOrderQueryByClient(Long userId,
                                                                     Integer orderStatus) {

        Map<String, Object> map = new HashMap<>();

        if (Objects.nonNull(userId)) {
            map.put("userId", userId);
        }

        if (Objects.nonNull(orderStatus)) {
            map.put("orderStatus", orderStatus);
        }

        return map;
    }

    private Map<String, Object> setParameterGetAllOrderQueryByAdmin(Integer orderStatus,
                                                                    String fromDate,
                                                                    String toDate,
                                                                    String search) {

        Map<String, Object> map = new HashMap<>();

        if (Objects.nonNull(orderStatus)) {
            map.put("orderStatus", orderStatus);
        }
        if (StringUtils.isNotBlank(search)) {
            map.put("search", "%" + search + "%");
        }

        if (StringUtils.isNotBlank(fromDate)) {
            map.put("fromDate", fromDate);
        }

        if (StringUtils.isNotBlank(toDate)) {
            toDate += " 23:59:59";
            map.put("toDate", toDate);
        }

        return map;
    }

    private Map<String, Object> setParameterGetOrderDetailByOrderId(Long orderId) {

        Map<String, Object> map = new HashMap<>();

        if (Objects.nonNull(orderId)) {
            map.put("orderId", orderId);
        }

        return map;
    }

    private Map<String, Object> setParameterGetOrderItemDetailByOrderId(Long orderId) {

        Map<String, Object> map = new HashMap<>();

        if (Objects.nonNull(orderId)) {
            map.put("orderId", orderId);
        }

        return map;
    }
}
