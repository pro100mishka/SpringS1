package com.geekbrains.spring.market.repositories;

import com.geekbrains.spring.market.entity.carts.CookieCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CookieCartRepository  extends JpaRepository<CookieCart,Long> {
    Optional<CookieCart> findBySessionId(String sessionId);
}
