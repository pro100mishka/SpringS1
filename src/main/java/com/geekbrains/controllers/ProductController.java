package com.geekbrains.controllers;

import com.geekbrains.entities.Product;
import com.geekbrains.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService){
        this.productService=productService;
    }

    @RequestMapping("/addProduct")
    public String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("product",product);
        return "product-form";
    }

    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("product") Product product) {
        if (!productService.addProduct(product)) return "error-page";
        return "product-success-add";
    }

    @RequestMapping(value = "/findById")
    public String findById(){
        return "product-find-form";
    }

    @RequestMapping(value = "/findByIdMethod" ,method = RequestMethod.GET)
    public String findByIdMethod(Model model, @RequestParam Long id){
        Product temp = productService.getById(id);
        if (temp==null)return "error-page";
        model.addAttribute(temp);
        return "product-result";
    }

    @RequestMapping(value = "/getAllProduct")
    public String getAllProduct(Model model){
        model.addAttribute("productList" ,productService.getAll());
        return "product-get-all";
    }
}
