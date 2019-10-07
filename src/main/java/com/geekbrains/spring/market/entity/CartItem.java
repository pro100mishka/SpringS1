package com.geekbrains.spring.market.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
@Data
@Accessors(chain = true)
public class CartItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;
}
