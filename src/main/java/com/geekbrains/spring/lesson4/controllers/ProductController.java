package com.geekbrains.spring.lesson4.controllers;

import com.geekbrains.spring.lesson4.entity.Product;
import com.geekbrains.spring.lesson4.filter.ProductFilterService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@Log4j2
public class ProductController {

    private ProductService productService;
    private ProductFilterService filterService;

    @Autowired
    public void setFilterService(ProductFilterService filterService) {
        this.filterService = filterService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all")
    public String getStart(Model model, HttpServletRequest f,
                           @ModelAttribute Product product,
                           @RequestParam(value = "word",required = false) String word,
                          // @RequestParam(value = "range",required = false) Double[] range,
                           @RequestParam(value = "page") Optional<Integer> page,
                           @RequestParam(value = "size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int currentSize = size.orElse(5);

        filterService.checkFilter(f);
        Specification<Product> specification = Specification.where(null);
        PageRequest request = PageRequest.of(currentPage-1, currentSize, Sort.by(Sort.Direction.DESC,"cost"));
        Page<Product> productPage = productService.findAllByPagingAndFiltering(specification,request);
       // if (range==null) range=new Double[]{0.0,productService.getMaxPrice()};
      //  model.addAttribute("range",range);
        model.addAttribute("newProduct",product);
        model.addAttribute("productPage", productPage);
        model.addAttribute("defaultMin", 0.0);
        model.addAttribute("defaultMax", productService.getMaxPrice());
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
