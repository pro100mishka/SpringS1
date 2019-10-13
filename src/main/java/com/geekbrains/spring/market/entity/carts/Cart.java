package com.geekbrains.spring.market.entity.carts;

import com.geekbrains.spring.market.entity.cartItems.CartItem;
import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Map;


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

//    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
//    @MapKeyJoinColumn(name = "product_id")
//    private Map<Product, CartItem> cartItemMap;
//
//    @Override
//    public String toString() {
//        return "Cart{" +
//                "id=" + id +
//                ", user=" + user.getUsername() +
//                ", cartItemMap=" + cartItemMap +
//                '}';
//    }
}
