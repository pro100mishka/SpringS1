package com.geekbrains.spring.market.repositories;

import com.geekbrains.spring.market.entity.Cart;
import com.geekbrains.spring.market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser(User user);
}