package com.geekbrains.spring.market.repositories;

import com.geekbrains.spring.market.entity.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem,Long> {
}
