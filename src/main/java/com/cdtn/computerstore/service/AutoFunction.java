package com.cdtn.computerstore.service;

import com.cdtn.computerstore.entity.Order;
import com.cdtn.computerstore.enums.OrderEnum;
import com.cdtn.computerstore.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AutoFunction {

    private final OrderRepository orderRepository;

    @Scheduled(fixedRate = 60000)
    private void updateOrderStatus() {

        List<Order> orderList = orderRepository.findOverdueOrder();
        for (Order order : orderList) {
            order.setStatus(OrderEnum.Status.CANCEL.getValue());
            order.setCanceledAt(LocalDateTime.now());
        }
        orderRepository.saveAll(orderList);
        System.out.println("Đã hủy " + orderList.size() + " đơn hàng");
    }
}
