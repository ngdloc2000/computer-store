package com.cdtn.computerstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
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
    private Integer brand;
    private String name;
    private String description;
    private Double price;
    private Double discount;
    private Integer quantity;
    private Integer sold;
    private Integer status;
    private Integer featured;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
