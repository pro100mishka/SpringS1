package com.geekbrains.spring.market.entity.cartItems;

import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.entity.carts.Cart;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_item")
@Data
@Accessors(chain = true)
public class CartItem implements CartItems{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "cart_id")
    private Cart cart;

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
        Long cartId = null;
        if (cart!=null) cartId = cart.getId();
        return "CartItem{" +
                "id=" + id +
                ", cart=" + cartId +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
