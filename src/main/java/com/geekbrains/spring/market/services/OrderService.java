package com.geekbrains.spring.market.services;

import com.geekbrains.spring.market.entity.order.OrderForTempUser;
import com.geekbrains.spring.market.entity.TempUser;
import com.geekbrains.spring.market.entity.order.OrderForTempUserItem;
import com.geekbrains.spring.market.repositories.OrderForTempUserRepository;
import com.geekbrains.spring.market.util.CookieUserHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Log4j2
public class OrderService {
    private UserServiceImpl userService;
    private CartService cartService;
    private OrderItemsService orderItemsService;
    private OrderForTempUserRepository orderForTempUserRepository;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderForTempUserRepository(OrderForTempUserRepository orderForTempUserRepository) {
        this.orderForTempUserRepository = orderForTempUserRepository;
    }

    @Autowired
    public void setOrderItemsService(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }


    public void confirmOrder(CookieUserHandler cookieUserHandler, TempUser tempUser){
        if (tempUser.getId()==null) tempUser = userService.saveTempUser(tempUser);
        OrderForTempUser order = getNewOrder(tempUser);
        cartService.getMapForPage(cookieUserHandler).forEach((product, cartItems) -> {
            OrderForTempUserItem item = new OrderForTempUserItem()
                    .setProduct(product)
                    .setOrder(order)
                    .setPrice(product.getCost())
                    .setQuantity(cartItems.getQuantity())
                    .setTotalPrice(cartItems.getTotalPrice());
            orderItemsService.save(item);
        });
        log.info("Cart delete: " + cartService.delete(cookieUserHandler));
    }

    private OrderForTempUser getNewOrder(TempUser tempUser){
        return orderForTempUserRepository.save(new OrderForTempUser().setTempUser(tempUser));
    }


}
