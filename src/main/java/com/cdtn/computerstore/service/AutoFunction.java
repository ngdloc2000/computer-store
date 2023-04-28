package com.cdtn.computerstore.service;

import com.cdtn.computerstore.entity.Order;
import com.cdtn.computerstore.entity.OrderItem;
import com.cdtn.computerstore.entity.Product;
import com.cdtn.computerstore.enums.OrderEnum;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.order.OrderRepository;
import com.cdtn.computerstore.repository.orderItem.OrderItemRepository;
import com.cdtn.computerstore.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AutoFunction {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Scheduled(fixedRate = 60000)
    private void updateOrderStatusOverdue() {

        List<Product> updatedProductList = new ArrayList<>();
        List<Order> updatedOrderList = orderRepository.findOverdueOrder();

        for (Order order : updatedOrderList) {
            order.setStatus(OrderEnum.Status.CANCEL.getValue());
            order.setCanceledAt(LocalDateTime.now());

            List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(order.getId());
            for (OrderItem item : orderItemList) {
                Optional<Product> product = productRepository.findById(item.getProductId());
                if (product.isEmpty()) {
                    continue;
                }
                product.get().setQuantity(product.get().getQuantity() + item.getQuantity());
                product.get().setUpdatedAt(LocalDateTime.now());
                updatedProductList.add(product.get());
            }
        }

        productRepository.saveAll(updatedProductList);
        orderRepository.saveAll(updatedOrderList);
        System.out.println("Đã hủy " + updatedOrderList.size() + " đơn hàng");
    }
}
