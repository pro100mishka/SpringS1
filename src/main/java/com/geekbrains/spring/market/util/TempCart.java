package com.geekbrains.spring.market.util;

import com.geekbrains.spring.market.entity.Cart;
import com.geekbrains.spring.market.entity.CartItem;
import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.services.CartItemService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@Log4j2
public class TempCart {

    private Cart cart;

    private CartItemService cartItemService;

    @Autowired
    public void setCartItemService(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    private Map<Product, CartItem> cartItemMap;

    @PostConstruct
    private void  init(){
        cartItemMap = new HashMap<>();
    }

    public void addToCart(Product product){
        CartItem temp = cartItemMap.get(product);
        if (temp==null){
            temp = cartItemService.getNew();
            temp.setProduct(product);
        } else {
            cartItemService.increase(temp);
        }
        log.info("Add product to cart: "+cartItemMap.put(product,temp));
    }
}
