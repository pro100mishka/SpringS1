package com.geekbrains.spring.market.services;

import com.geekbrains.spring.market.entity.CartItem;
import com.geekbrains.spring.market.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private CartItemRepository cartItemRepository;

    @Autowired
    public void setCartItemRepository(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem getNew(){
        return increase(new CartItem());
    }

    public CartItem save(CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }

    public CartItem increase(CartItem cartItem,int count){
        return cartItem.setCount(cartItem.getCount()+count);
    }

    public CartItem increase(CartItem cartItem){
        return cartItem.setCount(cartItem.getCount()+1);
    }
}
