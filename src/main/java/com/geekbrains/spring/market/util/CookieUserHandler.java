package com.geekbrains.spring.market.util;

import com.geekbrains.spring.market.entity.carts.Cart;
import com.geekbrains.spring.market.entity.carts.CookieCart;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@Log4j2
@Accessors(chain = true)
public class CookieUserHandler {


    private String sessionId;

    private Cart cart;

    private CookieCart cookieCart;

}
