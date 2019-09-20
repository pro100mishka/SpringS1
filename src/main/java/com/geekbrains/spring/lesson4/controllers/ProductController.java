package com.geekbrains.spring.lesson4.controllers;

import com.geekbrains.spring.lesson4.entity.Product;
import com.geekbrains.spring.lesson4.filter.PriceFilter;
import com.geekbrains.spring.lesson4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getStart(Model model,
                           @ModelAttribute PriceFilter priceFilter,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int currentSize = size.orElse(5);
        if (priceFilter.getRange()==null) priceFilter.init();
        Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage-1, currentSize, Sort.by(Sort.Direction.DESC,"cost")),priceFilter);
        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("productPage", productPage);
        model.addAttribute("priceFilter", priceFilter);
        return "product";
    }
}
