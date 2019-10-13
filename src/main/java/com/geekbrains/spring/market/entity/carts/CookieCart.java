package com.geekbrains.spring.market.entity.carts;


import com.geekbrains.spring.market.entity.cartItems.CookieCartItem;
import com.geekbrains.spring.market.entity.Product;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "cookie_carts")
@Data
@Accessors(chain = true)
public class CookieCart {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

//    @OneToMany(mappedBy = "cookieCart",cascade = CascadeType.ALL)
//    @MapKeyJoinColumn(name = "product_id")
//    private Map<Product, CookieCartItem> cartItemMap;
//
//    @Override
//    public String toString() {
//        return "CookieCart{" +
//                "id=" + id +
//                ", sessionId='" + sessionId + '\'' +
//                ", cartItemMap=" + cartItemMap +
//                '}';
//    }
}
