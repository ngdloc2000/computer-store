package com.cdtn.computerstore.dto.order.mapper;

import com.cdtn.computerstore.dto.cart.response.CartItemDetail;
import com.cdtn.computerstore.dto.order.request.OrderCreationForm;
import com.cdtn.computerstore.entity.Order;
import com.cdtn.computerstore.enums.OrderEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderMapper {

    public Order createOrder(OrderCreationForm form, List<CartItemDetail> cartItemDetailList) {

        Long totalPriceOrder = cartItemDetailList
                .stream()
                .mapToLong(CartItemDetail::getTotalPricePerProduct)
                .sum();

        return Order.builder()
                .clientId(form.getClientId())
                .status(OrderEnum.Status.WAIT.getValue())
                .total(totalPriceOrder)
                .consigneeName(form.getConsigneeName())
                .consigneePhoneNumber(form.getConsigneePhoneNumber())
                .deliveryAddress(form.getDeliveryAddress())
                .createAt(LocalDateTime.now())
                .checkoutSessionUrl(form.getCheckoutSessionUrl())
                .build();
    }
}
