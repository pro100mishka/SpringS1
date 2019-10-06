package com.geekbrains.spring.market.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne @NotNull
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "count")
    private int count;

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", count=" + count +
                '}';
    }
}
