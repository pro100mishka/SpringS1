package com.geekbrains.spring.lesson4.controllers;

import com.geekbrains.spring.lesson4.entity.Product;
import com.geekbrains.spring.lesson4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public String getStart(Model model){
        List<Product> productsList = productService.findAll();
        model.addAttribute("products",productsList);
        return "product";
    }

    @GetMapping("getMax")
    public String getMax(Model model){
        model.addAttribute("products",productService.findMax());
        return "product";
    }
    @GetMapping("/getMin")
    public String getMin(Model model){
        model.addAttribute("products",productService.findMin());
        return "product";
    }

    @GetMapping("/getMaxAndMin")
    public String getMaxAndMin(Model model){
        model.addAttribute("products",productService.findMaxAndMin());
        return "product";
    }
}
