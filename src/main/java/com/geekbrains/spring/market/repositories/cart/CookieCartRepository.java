package com.geekbrains.spring.market.repositories.cart;

import com.geekbrains.spring.market.entity.cart.CookieCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CookieCartRepository  extends JpaRepository<CookieCart,Long> {
    Optional<CookieCart> findBySessionId(String sessionId);
}
