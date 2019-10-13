package com.geekbrains.spring.market.entity.cartItems;

import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.entity.carts.CookieCart;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cookie_cart_item")
@Data
@Accessors(chain = true)
public class CookieCartItem implements CartItems {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "cart_id")
    private CookieCart cookieCart;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public Double getTotalPrice() {
        return product.getCost()*quantity;
    }

    @Override
    public String toString() {
        String sessionId = null;
        if (cookieCart!=null) sessionId = cookieCart.getSessionId();
        return "CookieCartItem{" +
                "id=" + id +
                ", cart=" + sessionId +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
