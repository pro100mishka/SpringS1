package com.geekbrains.spring.market.services;

import com.geekbrains.spring.market.entity.Cart;
import com.geekbrains.spring.market.entity.CartItem;
import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.entity.User;
import com.geekbrains.spring.market.repositories.CartRepository;
import com.geekbrains.spring.market.util.TempCart;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


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
        Cart cart = cartRepository.findByUser(user);
        if (cart==null){
            cart = getNewCart(user).setCartItems(null);
        }
            if (cart.getId()!=null){
                log.info("For User: "+ user.getUsername()+" create cart: " + cart.getCartItems());
                return cartRepository.save(cart);
            }
        log.info("For User: "+ user.getUsername()+" find cart: "+cart.getCartItems());
        return cart;
    }

    private Cart getNewCart(User user){
        return new Cart().setUser(user);
    }

    public Map<Product,CartItem> getCartItemsMap(TempCart tempCart){
        Map<Product,CartItem> temp = tempCart.getCartItemMap();
        Cart cart = tempCart.getCart();
        log.info("Current tempCart: " + temp);
        if (cart!=null){
            if (!temp.isEmpty())
                tempCart.getCartItemMap().forEach((product, cartItem) -> {
                    cartItem.setCart(cart);
                    log.info("CarItem save: "+cartItemService.save(cartItem));
                });
            log.info("Cart from database: "+cart.getCartItems());
            cart.getCartItems().forEach(cartItem -> {
                CartItem item = temp.get(cartItem.getProduct());
                if (item!=null){
                    cartItemService.increase(item,cartItem.getCount());
                } else temp.put(cartItem.getProduct(),cartItem);
            });
        }
        log.info("Before return: " + temp);
        return temp;
    }

    public CartItem saveCartItem(Product product, Cart cart){
        CartItem cartItem = cartItemService.getNew().setCart(cart).setProduct(product);
        cartItemService.save(cartItem);
        return cartItem;
    }
}


