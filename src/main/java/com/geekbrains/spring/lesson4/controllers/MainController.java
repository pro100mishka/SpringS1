package com.geekbrains.spring.lesson4.controllers;

import com.geekbrains.spring.lesson4.filter.PriceFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}