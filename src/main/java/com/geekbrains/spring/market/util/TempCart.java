package com.geekbrains.spring.market.util;

import com.geekbrains.spring.market.entity.Cart;
import com.geekbrains.spring.market.entity.CartItem;
import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.services.CartItemService;
import com.geekbrains.spring.market.services.CartService;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@Log4j2
@Accessors(chain = true)
public class TempCart {

    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    private Cart cart;

    private Map<Product, CartItem> tempProducts;

    @PostConstruct
    private void  init(){
        tempProducts = new LinkedHashMap<>();
    }

    public void addToCart(Product product){
        cartService.addToCart(product,this);
    }
}
