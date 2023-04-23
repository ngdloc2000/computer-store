package com.cdtn.computerstore.repository.order;

import com.cdtn.computerstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * " +
                    "from orders o " +
                    "where o.status = 1 " +
                            "and o.create_at >= current_timestamp " +
                            "and o.create_at <= DATE_ADD(o.create_at, INTERVAL 30 MINUTE)", nativeQuery = true)
    List<Order> findOverdueOrder();
}
