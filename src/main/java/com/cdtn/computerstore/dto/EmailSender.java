package com.cdtn.computerstore.dto;

import com.cdtn.computerstore.dto.order.response.OrderItemDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailSender {
    private String recipientEmail;
    private String fullName;
    private String subject;
    private Long orderId;
    private Long totalPrice;
    private String deliveryAddress;
    private List<OrderItemDetail> orderItemDetailList;
//    private String sendFrom;
//    private String content;
}