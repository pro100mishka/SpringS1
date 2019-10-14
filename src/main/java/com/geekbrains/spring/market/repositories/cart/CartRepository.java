package com.geekbrains.spring.market.repositories.cart;

import com.geekbrains.spring.market.entity.cart.Cart;
import com.geekbrains.spring.market.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser(User user);
}
