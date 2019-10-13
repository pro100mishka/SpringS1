package com.geekbrains.spring.market.repositories;

import com.geekbrains.spring.market.entity.carts.Cart;
import com.geekbrains.spring.market.entity.cartItems.CartItem;
import com.geekbrains.spring.market.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem,Long> {
    Optional<CartItem> findFirstByProductAndCart(Product product, Cart cart);
    List<CartItem> findAllByCart(Cart cart);
}
