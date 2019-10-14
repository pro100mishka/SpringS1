package com.geekbrains.spring.market.entity.order.item;


import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.entity.order.OrderForTempUser;
import com.geekbrains.spring.market.entity.order.OrderForUser;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "order_user_item")
@Data
@Accessors(chain = true)
public class OrderForUserItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderForUser order;

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
