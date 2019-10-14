package com.geekbrains.spring.market.entity.cart.item;

import java.math.BigDecimal;

public interface CartItems {
    String toString();
    Double getTotalPrice();
    int getQuantity();
}
