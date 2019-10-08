package com.geekbrains.spring.market.services;

import com.geekbrains.spring.market.entity.Cart;
import com.geekbrains.spring.market.entity.CartItem;
import com.geekbrains.spring.market.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Log4j2
public class CartItemService {

    public CartItem getNewCartItem(Product product, Cart cart){
        return new CartItem().setProduct(product).setCart(cart).setQuantity(0);
    }

    public CartItem addProduct(Product product,Cart cart, CartItem cartItem){
        if (cartItem==null){
            cartItem = getNewCartItem(product,cart);
        }
        return cartItem.setQuantity(cartItem.getQuantity()+1);
    }

    public void addProductToCartMap(Product product, Cart cart, CartItem cartItem){
        Map<Product,CartItem> mapInCart = cart.getCartItemMap();
        log.info("Before convert tempCart to cart: " + cart.getCartItemMap());
        CartItem temp = mapInCart.get(product);
        if (temp!=null){
            temp.setQuantity(temp.getQuantity()+cartItem.getQuantity());
        } else mapInCart.put(product,cartItem.setCart(cart));
        log.info("After convert tempCart to cart: " + cart.getCartItemMap());
    }
}
