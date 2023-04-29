package com.cdtn.computerstore.repository.orderItem;

import com.cdtn.computerstore.dto.order.response.OrderDetail;
import com.cdtn.computerstore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrderId(Long orderId);

//    @Query(value = "", nativeQuery = true)
//    List<OrderDetail> findByOrderId(Long orderId);
}
