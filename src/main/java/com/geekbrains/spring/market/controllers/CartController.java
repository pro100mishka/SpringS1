package com.geekbrains.spring.market.controllers;

import com.geekbrains.spring.market.entity.cartItems.CartItems;
import com.geekbrains.spring.market.entity.carts.Cart;
import com.geekbrains.spring.market.entity.cartItems.CartItem;
import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.entity.TempUser;
import com.geekbrains.spring.market.errors_handler.exceptions.NotFoundException;
import com.geekbrains.spring.market.services.CartService;
import com.geekbrains.spring.market.services.ProductService;
import com.geekbrains.spring.market.util.CookieUserHandler;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

import java.util.Map;


@Controller
@Log4j2
@RequestMapping("/cart")
@Accessors(chain = true)
public class CartController {

    private CookieUserHandler cookieUserHandler;
    private ProductService productService;
    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setCookieUserHandler(CookieUserHandler cookieUserHandler) {
        this.cookieUserHandler = cookieUserHandler;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/add/{id}")
    public void addToCart(HttpServletRequest request, HttpServletResponse response,
                          @PathVariable(name = "id") Long id) throws IOException {
        Product product = productService.findById(id).orElseThrow(()->new NotFoundException("Product by id: "+id+" not found."));
        if (cookieUserHandler.getSessionId()==null) cookieUserHandler.setSessionId(request.getCookies()[0].getValue());
        cartService.addProductToCart(product,cookieUserHandler);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping
    public String showCart(Model model){
        Map <Product, CartItems> productCountMap = cartService
                .getMapForPage(cookieUserHandler);
        model.addAttribute("tempUser", new TempUser());
        model.addAttribute("cartList", productCountMap);
        return "cart";
    }

    @GetMapping(value = "/bind")
    public String bindUserForCart(Principal principal){
        cartService.bindCart(cookieUserHandler,principal.getName());
        return "redirect:/shop";
    }

}
