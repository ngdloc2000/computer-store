package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.cart.response.CartItemDetail;
import com.cdtn.computerstore.dto.order.mapper.OrderMapper;
import com.cdtn.computerstore.dto.order.request.OrderCreationForm;
import com.cdtn.computerstore.dto.order.response.OrderDetail;
import com.cdtn.computerstore.dto.order.response.OrderInfoAdminSearch;
import com.cdtn.computerstore.dto.order.response.OrderInfoClientSearch;
import com.cdtn.computerstore.entity.Cart;
import com.cdtn.computerstore.entity.Order;
import com.cdtn.computerstore.entity.OrderItem;
import com.cdtn.computerstore.entity.Product;
import com.cdtn.computerstore.enums.CartEnum;
import com.cdtn.computerstore.enums.OrderEnum;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.cart.CartRepository;
import com.cdtn.computerstore.repository.cartItem.CustomCartItemRepositoryImpl;
import com.cdtn.computerstore.repository.order.CustomOrderRepositoryImpl;
import com.cdtn.computerstore.repository.order.OrderRepository;
import com.cdtn.computerstore.repository.orderItem.OrderItemRepository;
import com.cdtn.computerstore.repository.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final OrderItemRepository orderItemRepository;
    private final CustomOrderRepositoryImpl customOrderRepository;

    private final OrderMapper orderMapper;

    @Transactional
    public Long createOrder(OrderCreationForm form) {

        Cart cart = cartRepository.findByClientIdAndActiveCart(form.getClientId())
                .orElseThrow(() -> new StoreException("Giỏ hàng không tồn tại hoặc trạng thái đang không hoạt động"));
        List<CartItemDetail> cartItemDetailList = customCartItemRepository.getItemActiveInCart(cart.getId(), form.getClientId());

        // Trừ số lượng sản phẩm
        List<Product> productList = this.updateProductInCart(cartItemDetailList);
        productRepository.saveAll(productList);

        // Cập nhật giỏ hàng
        cart.setStatus(CartEnum.Status.ORDERED.getValue());
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);

        // Tạo order
        Order order = orderRepository.save(orderMapper.createOrder(form, cartItemDetailList));
        // Tạo order item
        saveOrderItem(order.getId(), cartItemDetailList);

        return order.getId();
    }

    @Transactional
    public void payment(boolean isPaid, Long orderId) {

        if (isPaid) {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new StoreException("không tìm thấy Order với ID: " + orderId));
            order.setStatus(OrderEnum.Status.PAID.getValue());
            order.setCompletedAt(LocalDateTime.now());
            orderRepository.save(order);
            updateQuantitySoldProductWhenPaymentSuccess(orderId);
        } else {
            throw new StoreException("Chưa thanh toán thành công");
        }
    }

    private void saveOrderItem(Long orderId, List<CartItemDetail> cartItemDetailList) {

        List<OrderItem> orderItemList = new ArrayList<>();

        for (CartItemDetail item : cartItemDetailList) {
            OrderItem orderItem = OrderItem.builder()
                    .orderId(orderId)
                    .productId(item.getProductId())
                    .quantity(item.getItemQuantity())
                    .price(item.getTotalPricePerProduct())
                    .createAt(LocalDateTime.now())
                    .build();
            orderItemList.add(orderItem);
        }

        orderItemRepository.saveAll(orderItemList);
    }

    public List<OrderInfoClientSearch> getAllOrderByClient(Long userId,
                                                           Integer orderStatus,
                                                           Integer page,
                                                           Integer size) {

        Page<OrderInfoClientSearch> orderPage = customOrderRepository.getAllOrderByClient(userId, orderStatus, page, size);
        List<OrderInfoClientSearch> orderList = orderPage.getContent();

        return orderList;
    }

    public List<OrderInfoAdminSearch> getAllOrderByAdmin(Integer orderStatus,
                                                         String fromDate,
                                                         String toDate,
                                                         Integer page,
                                                         Integer size) {

        Page<OrderInfoAdminSearch> orderPage = customOrderRepository.getAllOrderByAdmin(orderStatus, fromDate, toDate, page, size);
        List<OrderInfoAdminSearch> orderList = orderPage.getContent();

        return orderList;
    }

    public OrderDetail getOrderDetail(Long orderId) {
        return customOrderRepository.getOrderDetailByOrderId(orderId);
    }

    private void updateQuantitySoldProductWhenPaymentSuccess(Long orderId) {

        List<Product> updatedProductList = new ArrayList<>();
        List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(orderId);

        for (OrderItem item : orderItemList) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new StoreException("Không tìm thấy Product với ID: " + item.getProductId()));
            product.setSold(product.getSold() + item.getQuantity());
            product.setUpdatedAt(LocalDateTime.now());
            updatedProductList.add(product);
        }

        productRepository.saveAll(updatedProductList);
    }

    /**
     * Kiểm tra và cập nhật số lượng tồn kho mỗi sản phẩm trong giỏ hàng trước khi tạo đơn hàng
     *
     * @param cartItemDetailList
     * @return
     */
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
                product.setQuantity(product.getQuantity() - item.getItemQuantity());
                product.setUpdatedAt(LocalDateTime.now());
                productList.add(product);
            }
        }

        return productList;
    }
}
