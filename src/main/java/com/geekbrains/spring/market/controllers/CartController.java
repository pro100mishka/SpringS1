package com.geekbrains.spring.market.controllers;

import com.geekbrains.spring.market.entity.Cart;
import com.geekbrains.spring.market.entity.CartItem;
import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.services.CartService;
import com.geekbrains.spring.market.services.ProductService;
import com.geekbrains.spring.market.util.TempCart;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/cart")
public class CartController {

    private TempCart tempCart;
    private ProductService productService;
    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setTempCart(TempCart tempCart) {
        this.tempCart = tempCart;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/add/{id}")
    public void addToCart(HttpServletRequest request, HttpServletResponse response,
                          @PathVariable(name = "id") Long id) throws IOException {
        Product product = productService.findById(id).get();
        if (request.getRemoteUser()!=null){
//            cartService.saveCartItem
        } else {
            tempCart.addToCart(product);
        }
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping
    public String showCart(Model model,
                           HttpServletRequest request){
        Map<Product, CartItem> mapForPage = cartService.getCartItemsMap(tempCart);
        log.info(mapForPage);
        model.addAttribute("cartList", mapForPage);
        return "cart";
    }

    @GetMapping(value = "/bind")
    public String bindUserForCart(HttpServletRequest request){
        Cart cart = cartService.getCart(request.getRemoteUser());
        tempCart.setCart(cart);
        return "redirect:/shop";
    }
}
