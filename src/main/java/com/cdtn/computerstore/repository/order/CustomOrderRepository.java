package com.cdtn.computerstore.repository.order;

import com.cdtn.computerstore.dto.order.response.OrderInfoClientSearch;
import org.springframework.data.domain.Page;

public interface CustomOrderRepository {

    Page<OrderInfoClientSearch> getAllOrderByClient(Long userId, Integer orderStatus, Integer page, Integer size);
}
