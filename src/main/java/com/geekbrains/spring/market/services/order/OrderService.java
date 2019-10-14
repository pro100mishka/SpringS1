package com.geekbrains.spring.market.services.order;

import com.geekbrains.spring.market.entity.order.OrderForTempUser;
import com.geekbrains.spring.market.entity.order.OrderForUser;
import com.geekbrains.spring.market.entity.order.item.OrderForUserItem;
import com.geekbrains.spring.market.entity.user.TempUser;
import com.geekbrains.spring.market.entity.order.item.OrderForTempUserItem;
import com.geekbrains.spring.market.entity.user.User;
import com.geekbrains.spring.market.repositories.order.OrderForTempUserRepository;
import com.geekbrains.spring.market.repositories.order.OrderForUserRepository;
import com.geekbrains.spring.market.services.order.item.OrderItemsService;
import com.geekbrains.spring.market.services.user.UserServiceImpl;
import com.geekbrains.spring.market.services.cart.CartService;
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
    private OrderForUserRepository orderForUserRepository;

    @Autowired
    public void setOrderForUserRepository(OrderForUserRepository orderForUserRepository) {
        this.orderForUserRepository = orderForUserRepository;
    }

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
        tempUser = userService.saveTempUser(tempUser);
        OrderForTempUser order = getNewOrderForTempUser(tempUser);
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

    public void confirmOrder(CookieUserHandler cookieUserHandler){
        User user = cookieUserHandler.getCart().getUser();
        OrderForUser order = getNewOrderForUser(user);
        cartService.getMapForPage(cookieUserHandler).forEach((product, cartItems) -> {
            OrderForUserItem item = new OrderForUserItem()
                    .setProduct(product)
                    .setOrder(order)
                    .setPrice(product.getCost())
                    .setQuantity(cartItems.getQuantity())
                    .setTotalPrice(cartItems.getTotalPrice());
            orderItemsService.save(item);
        });
        log.info("Cart delete: " + cartService.delete(cookieUserHandler));
    }

    private OrderForTempUser getNewOrderForTempUser(TempUser tempUser){
        return orderForTempUserRepository.save(new OrderForTempUser().setTempUser(tempUser));
    }

    private OrderForUser getNewOrderForUser(User user){
        return orderForUserRepository.save(new OrderForUser().setUser(user));
    }

}
