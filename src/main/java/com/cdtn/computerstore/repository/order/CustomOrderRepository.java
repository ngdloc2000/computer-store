package com.cdtn.computerstore.repository.order;

import com.cdtn.computerstore.dto.order.response.OrderDetail;
import com.cdtn.computerstore.dto.order.response.OrderInfoAdminSearch;
import com.cdtn.computerstore.dto.order.response.OrderInfoClientSearch;
import com.cdtn.computerstore.dto.order.response.OrderItemDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomOrderRepository {

    Page<OrderInfoClientSearch> getAllOrderByClient(Long userId, Integer orderStatus, Integer page, Integer size);

    Page<OrderInfoAdminSearch> getAllOrderByAdmin(Integer orderStatus, String fromDate, String toDate, Integer page, Integer size);

    OrderDetail getOrderDetailByOrderId(Long orderId);

    List<OrderItemDetail> getOrderItemDetailByOrderId(Long orderId);
}
