package com.cdtn.computerstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private Integer status;
    private Long total;
    private String consigneeName;
    private String consigneePhoneNumber;
    private String deliveryAddress;
    private Integer paymentMethod;
    private LocalDateTime createAt;
    private LocalDateTime canceledAt;
    private LocalDateTime completedAt;
    @Column(columnDefinition = "TEXT")
    private String checkoutSessionUrl;
}
