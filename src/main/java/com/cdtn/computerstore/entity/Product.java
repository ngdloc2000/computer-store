package com.cdtn.computerstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long categoryId;
    private String name;
    private Integer brand;
    private String description;
    private Long retailPrice;
    private Long latestPrice;
    private Double discount;
    private Integer quantity;
    private Integer sold;
    private Integer status;
    private Integer featured;
    private Integer warranty;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
