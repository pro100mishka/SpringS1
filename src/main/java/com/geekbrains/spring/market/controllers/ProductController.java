package com.geekbrains.spring.market.controllers;

import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.services.filter_services.ProductFilterAndPageService;
import com.geekbrains.spring.market.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@Log4j2
public class ProductController {

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

    @GetMapping(value = "/")
    public String getStart(Model model, HttpServletRequest httpServletRequest,
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
        return "product";
    }


    @GetMapping(value = "/edit/{id}")
    public String edit(Model model,
                       @PathVariable(name = "id") Long id){
        Product product = productService.findById(id).get();  //тут Тогда надо написать экспешен.
        model.addAttribute("product",product);
        return "edit_product";
    }
    @PostMapping(value = "/edit")
    public String update(@ModelAttribute(name="product") Product product){
        log.info("Product: "+product+" Success update: "+productService.update(product));  // id нет ошибка
        return "redirect:/products/all";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute(name = "product") Product product){
        log.info("Product: "+product+" Success add: "+productService.update(product));  //id есть ошибка
        return "redirect:/products/all";
    }
}
