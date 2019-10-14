package com.geekbrains.spring.market.controllers;

import com.geekbrains.spring.market.entity.user.TempUser;
import com.geekbrains.spring.market.entity.user.User;
import com.geekbrains.spring.market.services.order.OrderService;
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

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setCookieUserHandler(CookieUserHandler cookieUserHandler) {
        this.cookieUserHandler = cookieUserHandler;
    }

    @PostMapping("tempUserOrder")
    public String confirmationOrderFromTempUser(@ModelAttribute TempUser tempUser){
        orderService.confirmOrder(cookieUserHandler,tempUser);
        log.info("User confirmation Order: " + tempUser);
        return "redirect:/shop";
    }

    @PostMapping("userOrder")
    public String confirmationOrderFromUser(){
        User user = cookieUserHandler.getCart().getUser();
        orderService.confirmOrder(cookieUserHandler);
        log.info("User confirmation Order: " + user);
        return "redirect:/shop";
    }
}
