package com.transactional.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer orderId;

    private int productId;
    private int qty;
    private double totalPrice;

}
