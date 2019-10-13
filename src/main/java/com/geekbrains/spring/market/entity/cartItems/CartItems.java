package com.geekbrains.spring.market.entity.cartItems;

import java.math.BigDecimal;

public interface CartItems {
    String toString();
    Double getTotalPrice();
    int getQuantity();
}
