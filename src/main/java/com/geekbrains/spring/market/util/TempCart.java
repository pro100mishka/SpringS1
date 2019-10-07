package com.geekbrains.spring.market.util;

import com.geekbrains.spring.market.entity.Cart;
import com.geekbrains.spring.market.entity.Product;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@Log4j2
public class TempCart {

    private Cart cart;

    private List<Product> tempProducts;

    @PostConstruct
    private void  init(){
        tempProducts = new ArrayList<>();
    }

    public void addToCart(Product product){
        log.info("Add product to cart: "+ tempProducts.add(product));
    }
}
