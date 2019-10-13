package com.geekbrains.spring.market.repositories;

import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.entity.cartItems.CookieCartItem;
import com.geekbrains.spring.market.entity.carts.CookieCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CookieCartItemRepository extends JpaRepository<CookieCartItem,Long> {
    Optional<CookieCartItem> findFirstByProductAndCookieCart(Product product, CookieCart cookieCart);
    List<CookieCartItem> findAllByCookieCart(CookieCart cookieCart);
}
