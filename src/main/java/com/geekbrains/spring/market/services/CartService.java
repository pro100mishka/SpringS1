package com.geekbrains.spring.market.services;

import com.geekbrains.spring.market.entity.Cart;
import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.entity.User;
import com.geekbrains.spring.market.repositories.CartRepository;
import com.geekbrains.spring.market.util.TempCart;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


@Service
@Log4j2
public class CartService {
    private CartRepository cartRepository;
    private UserServiceImpl userService;

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
            log.info("For User: "+ user.getUsername()+" create cart: " + cart.getProducts());
            return cartRepository.save(cart);
        }
        log.info("For User: "+ user.getUsername()+" find cart: "+cart.getProducts());
        return cart;
    }

    private Cart getNewCartByUser(User user){
        return new Cart().setUser(user).setProducts(new ArrayList<>());
    }

    public Cart saveToCart(Product product,Cart cart){
        cart.getProducts().add(product);
        log.info("Product add to cart: " + product);
        return saveCart(cart);
    }

    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }

    public Map<Product,Long> getProductMap(TempCart tempCart){
        Map<Product, Long> productCountMap = null;
        if  (tempCart.getCart()!=null){
            productCountMap = listToMap(tempCart.getCart().getProducts());
        } else {
            productCountMap = listToMap(tempCart.getTempProducts());
        }
        return productCountMap;
    }

    private Map<Product,Long> listToMap(List<Product> productList){
        return productList
                .stream()
                .collect(groupingBy(Function.identity(), counting()));
    }
}


