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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


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
        if (cart.getId()==null){
            log.info("For User: "+ user.getUsername()+" create cart: " + cart.getCartItemMap());
            return cartRepository.save(cart);
        }
        log.info("For User: "+ user.getUsername()+" find cart: "+cart.getCartItemMap());
        return cart;
    }

    private Cart getNewCartByUser(User user){
        return new Cart().setUser(user).setCartItemMap(new LinkedHashMap<>());
    }

    public void addToCart(Product product,TempCart tempCart){
        Cart cart = tempCart.getCart();
        if (cart != null){
            CartItem cartItem = cart.getCartItemMap().get(product);
            cartItem = cartItemService.addProduct(product,cart,cartItem);
            log.info("Product add to cart: " + cartItem);
            cart.getCartItemMap().put(product,cartItem);
            cartRepository.save(cart);
        }
        CartItem cartItem = tempCart.getTempProducts().get(product);
        cartItem = cartItemService.addProduct(product,cart,cartItem);
        log.info("CartItem to map: " + cartItem);
        tempCart.getTempProducts().put(product,cartItem);
    }

    public void addTempCartToCart(TempCart tempCart){
        Cart cart = tempCart.getCart();
        if (!tempCart.getTempProducts().isEmpty()){
            tempCart.getTempProducts().
                    forEach((product, cartItem) -> cartItemService.addProductToCartMap(product,cart,cartItem));
        }
        log.info("Save cart to BD: " + cartRepository.save(cart).getCartItemMap());
    }

    public Map<Product,CartItem> getMapForPage(TempCart tempCart){
        if (tempCart.getCart()!=null){
            return tempCart.getCart().getCartItemMap();
        }
        return tempCart.getTempProducts();
    }
}


