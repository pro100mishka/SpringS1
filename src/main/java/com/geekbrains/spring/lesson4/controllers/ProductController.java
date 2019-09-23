package com.geekbrains.spring.lesson4.controllers;

import com.geekbrains.spring.lesson4.entity.Product;
import com.geekbrains.spring.lesson4.filter.Filter;
import com.geekbrains.spring.lesson4.services.ProductService;
import com.geekbrains.spring.lesson4.services.ProductSpecificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/products")
@Log4j2
public class ProductController {

    private ProductService productService;
    private ProductSpecificationService specService;

    @Autowired
    public void setSpecService(ProductSpecificationService specService) {
        this.specService = specService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getStart(Model model,
                           @ModelAttribute Filter filter,
                           @ModelAttribute Product product,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int currentSize = size.orElse(5);

        Specification<Product> specification = specService.getByFilter(specService.checkFilter(filter,currentSize));
        PageRequest request = PageRequest.of(currentPage-1, currentSize, Sort.by(Sort.Direction.DESC,"cost"));
        System.out.println(filter);
        Page<Product> productPage = productService.findAllByPagingAndFiltering(specification,request);
        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("newProduct",product);
        model.addAttribute("productPage", productPage);
        model.addAttribute("filter", filter);
        return "product";
    }
    @GetMapping("/edit/{id}")
    public String edit(Model model,
                       @PathVariable(name = "id") Long id){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "edit_product";
    }
    @PostMapping("/edit")
    public String update(@ModelAttribute(name="product") Product product){
        log.info("Product: "+product+" Success update: "+productService.update(product));
        return "redirect:/products/all";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute(name = "product") Product product){
        log.info("Product: "+product+" Success add: "+productService.update(product));
        return "redirect:/products/all";
    }
}
