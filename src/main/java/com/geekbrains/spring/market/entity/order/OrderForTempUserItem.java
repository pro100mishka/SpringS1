package com.geekbrains.spring.market.entity.order;

import com.geekbrains.spring.market.entity.Product;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "order_temp_items")
@Data
@Accessors(chain = true)
public class OrderForTempUserItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderForTempUser order;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @Column(name = "price")
    private Double price;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "quantity")
    private int quantity;
}
