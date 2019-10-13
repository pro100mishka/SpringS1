package com.geekbrains.spring.market.controllers;


import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.services.ProductService;
import com.geekbrains.spring.market.services.filter_services.ProductFilterAndPageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

@RequestMapping("/shop")
@Controller
@Log4j2
public class ShopController {


    private ProductService productService;
    private ProductFilterAndPageService filterAndPageService;

    @Autowired
    public void setFilterService(ProductFilterAndPageService filterService) {
        this.filterAndPageService = filterService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String getStart(Model model,
                           @ModelAttribute Product product,
                           @RequestParam(value = "word") Optional<String> word,
                           @RequestParam(value = "range") Optional<Double[]> range,
                           @RequestParam(value = "page") Optional<Integer> page,
                           @RequestParam(value = "size") Optional<Integer> size){
        Specification<Product> specification = filterAndPageService.getSpecification(word, range);
        PageRequest request = filterAndPageService.getPageRequest(page, size);
        Page<Product> productPage = productService.findAllByPagingAndFiltering(specification,request);
        model.addAttribute("filter", filterAndPageService.getFilter());
        model.addAttribute("newProduct", product);
        model.addAttribute("productPage", productPage);
        return "shop";
    }
}
