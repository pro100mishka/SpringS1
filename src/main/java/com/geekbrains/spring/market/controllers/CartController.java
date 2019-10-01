package com.geekbrains.spring.market.controllers;

import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
@Log4j2
public class CartController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/add/{id}")
    public void addToCart(HttpServletRequest request, HttpServletResponse response,
                          @PathVariable(name = "id") Long id) throws IOException {
        Product product = productService.findById(id).get();
        log.info("Product: "+product+" success add!");
        response.sendRedirect(request.getHeader("referer"));
    }

}
