package com.cdtn.computerstore.repository.order;

import com.cdtn.computerstore.dto.order.response.OrderInfoClientSearch;
import com.cdtn.computerstore.enums.ProductEnum;
import com.cdtn.computerstore.exception.StoreException;
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

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Page<OrderInfoClientSearch> getAllOrderByClient(Long userId, Integer orderStatus, Integer page, Integer size) {
        try {
            List<OrderInfoClientSearch> orderList = jdbcTemplate.query(
                    createGetAllOrderQueryByClient(userId, orderStatus),
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
                                    : LocalDateTime.parse(rs.getString("completedAt"), dateTimeFormatter)
                    )
            );

            return PageUtil.getPage(orderList, page, size);
        } catch (Exception e) {
            throw new StoreException(e.getMessage());
        }
    }

    private String createGetAllOrderQueryByClient(Long userId,
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
                       o.completed_at       as completedAt
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
}
