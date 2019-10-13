package com.geekbrains.spring.market.controllers;

import com.geekbrains.spring.market.entity.TempUser;
import com.geekbrains.spring.market.util.CookieUserHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/")
@Log4j2
public class OrderController {

    private CookieUserHandler cookieUserHandler;

    @Autowired
    public void setCookieUserHandler(CookieUserHandler cookieUserHandler) {
        this.cookieUserHandler = cookieUserHandler;
    }

    @PostMapping("tempUserOrder")
    public String confirmationOrderFromTempUser(@ModelAttribute TempUser tempUser){
        log.info("User confirmation Order"+tempUser);
        return "redirect:/shop";
    }
}
