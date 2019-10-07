package com.geekbrains.spring.market.services;

import com.geekbrains.spring.market.entity.Cart;
import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.entity.User;
import com.geekbrains.spring.market.repositories.CartRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@Log4j2
public class CartService {
    private CartRepository cartRepository;
    private UserServiceImpl userService;
    private CartItemService cartItemService;

    @Autowired
    public void setCartItemService(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public Cart getCart(String username){
        User user = userService.findByUsername(username);
        log.info("User search cart: "+user.getUsername());
        Cart cart = cartRepository.findByUser(user).orElse(getNewCartByUser(user));
        if (cart.getId()!=null){
            log.info("For User: "+ user.getUsername()+" create cart: " + cart.getProducts());
            return cartRepository.save(cart);
        }
        log.info("For User: "+ user.getUsername()+" find cart: "+cart.getProducts());
        return cart;
    }

    private Cart getNewCartByUser(User user){
        return new Cart().setUser(user).setProducts(new ArrayList<>());
    }

    public void saveToCart(Product product,Cart cart){
        log.info("Product add to cart: "+cartItemService.save(product, cart).getProduct());
    }

    public void saveCart(Cart cart){
        cartRepository.save(cart);
    }
}


