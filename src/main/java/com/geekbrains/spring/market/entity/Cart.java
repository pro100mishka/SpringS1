package com.geekbrains.spring.market.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;



@Entity
@Table(name = "carts")
@Data
@Accessors(chain = true)
public class Cart {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;

    @OneToMany (mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItem> cartItems;
}
