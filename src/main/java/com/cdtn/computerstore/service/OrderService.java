package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.cart.response.CartItemDetail;
import com.cdtn.computerstore.dto.order.mapper.OrderMapper;
import com.cdtn.computerstore.dto.order.request.CreationOrderForm;
import com.cdtn.computerstore.dto.product.mapper.ProductMapper;
import com.cdtn.computerstore.entity.Cart;
import com.cdtn.computerstore.entity.Order;
import com.cdtn.computerstore.entity.Product;
import com.cdtn.computerstore.enums.CartEnum;
import com.cdtn.computerstore.enums.OrderEnum;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.cart.CartRepository;
import com.cdtn.computerstore.repository.cartItem.CustomCartItemRepositoryImpl;
import com.cdtn.computerstore.repository.order.OrderRepository;
import com.cdtn.computerstore.repository.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CustomCartItemRepositoryImpl customCartItemRepository;

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;

    @Transactional
    public Long createOrder(CreationOrderForm form) {

        Cart cart = cartRepository.findByClientIdAndActiveCart(form.getClientId())
                .orElseThrow(() -> new StoreException("Giỏ hàng không tồn tại hoặc trạng thái đang không hoạt động"));
        List<CartItemDetail> cartItemDetailList = customCartItemRepository.getItemActiveInCart(cart.getId(), form.getClientId());
        List<Product> productList = this.updateProductInCart(cartItemDetailList);

        cart.setStatus(CartEnum.Status.ORDERED.getValue());
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);

        Order order = orderMapper.createOrder(form, cartItemDetailList);
        orderRepository.save(order);

        productRepository.saveAll(productList);

        return order.getId();
    }

    @Transactional
    public void payment(boolean isPaid, Long orderId) {

        if (isPaid) {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new StoreException("không tìm thấy Order với ID: " + orderId));
            order.setStatus(OrderEnum.Status.PAID.getValue());
            order.setCompletedAt(LocalDateTime.now());
        } else {
            throw new StoreException("Chưa thanh toán thành công");
        }
    }

    private List<Product> updateProductInCart(List<CartItemDetail> cartItemDetailList) {

        List<Product> productList = new ArrayList<>();

        if (Objects.isNull(cartItemDetailList)) {
            throw new StoreException("Không có sản phẩm để tạo hóa đơn");
        } else {
            for (CartItemDetail item : cartItemDetailList) {
                Product product = productRepository.findProductActiveById(item.getProductId())
                        .orElseThrow(() -> new StoreException("Sản phẩm không hoạt động"));
                if (item.getItemQuantity() > product.getQuantity()) {
                    throw new StoreException("SL sản phẩm đặt hàng có ID là " + product.getId() + " vượt quá SL tồn kho");
                }
                productMapper.updateProductInCart(product, item.getItemQuantity());
                productList.add(product);
            }
        }

        return productList;
    }

    @Scheduled(cron = "0 * * * * *")
    private void updateOrderStatus() {

        List<Order> orderList = orderRepository.findOverdueOrder();
        for (Order order : orderList) {
            order.setStatus(OrderEnum.Status.CANCEL.getValue());
            order.setCanceledAt(LocalDateTime.now());
        }
        orderRepository.saveAll(orderList);
    }
}
